package ru.ikm.utilsforprisonikm.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ikm.utilsforprisonikm.entity.*;
import ru.ikm.utilsforprisonikm.repository.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class addController {

    private final ArticleRepository articleRepository;
    private final CasteRepository casteRepository;
    private final GangRepository gangRepository;
    private final MemberRepository memberRepository;
    private final PrisonRepository prisonRepository;

    // Add
    @GetMapping("/addMember")
    public String showAddMemberForm(Model model) {
        List<Prison> prisons = prisonRepository.findAll();

        List<Caste> castes = casteRepository.findAll();
        List<Gang> gangs = gangRepository.findAll();
        model.addAttribute("member", new Member());
        model.addAttribute("prisons", prisons);
        model.addAttribute("castes", castes);
        model.addAttribute("gangs", gangs);
        return "addMember";
    }

    @PostMapping("/addMember")
    public String addMember(@Valid @ModelAttribute Member member, BindingResult bindingResult, @RequestParam(value = "prisonId", required = false) Long prisonId, @RequestParam(value = "casteId", required = false) Long casteId, @RequestParam(value = "gangId", required = false) Long gangId, @RequestParam("articleNumber") String articleNumber, @RequestParam("articleDescription") String articleDescription, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Ошибка валидации. Пожалуйста, проверьте введенные данные.");
            return "addMember";
        }

        if (prisonId != null) {
            Prison prison = prisonRepository.findById(prisonId).orElse(null);
            member.setPrison(prison);
        }
        if (casteId != null) {
            Caste caste = casteRepository.findById(casteId).orElse(null);
            member.setCaste(caste);
        }
        if (gangId != null) {
            Gang gang = gangRepository.findById(gangId).orElse(null);
            member.setGang(gang);
        }
        member.setJoinedDate(LocalDate.now());

        Article newArticle = new Article();
        newArticle.setArticleNumber(articleNumber);
        newArticle.setArticleDescription(articleDescription);
        newArticle.setName(articleNumber);
        if (articleRepository.existsByName(newArticle.getName())) {
            model.addAttribute("errorMessage", "Статья с таким именем уже существует.");
            return "addMember";
        }

        articleRepository.save(newArticle);
        member.setArticle(newArticle);
        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/addCaste")
    public String showAddCasteForm(Model model) {
        model.addAttribute("caste", new Caste());
        return "addCaste";
    }

    @PostMapping("/addCaste")
    public String addCaste(@Valid @ModelAttribute Caste caste, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Ошибка валидации. Пожалуйста, проверьте введенные данные.");
            return "addCaste";
        }

        casteRepository.save(caste);
        return "redirect:/";
    }

    @GetMapping("/addPrison")
    public String showAddPrisonForm(Model model) {
        model.addAttribute("prison", new Prison());
        return "addPrison";
    }

    @PostMapping("/addPrison")
    public String addPrison(@Valid @ModelAttribute Prison prison, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Ошибка валидации. Пожалуйста, проверьте введенные данные.");
            return "addPrison";
        }
        if (prisonRepository.existsByNameAndCity(prison.getName(), prison.getCity())) {
            model.addAttribute("errorMessage", "Тюрьма с таким именем и городом уже существует.");
            return "addPrison";
        }
        prisonRepository.save(prison);
        return "redirect:/AllPrison";
    }

    @GetMapping("/addGang")
    public String showAddGangForm(Model model) {
        List<Prison> prisons = prisonRepository.findAll();
        model.addAttribute("gang", new Gang());
        model.addAttribute("prisons", prisons);
        return "addGang";
    }

    @PostMapping("/addGang")
    public String addGang(@Valid @ModelAttribute Gang gang, BindingResult bindingResult, @RequestParam("prisonId") Long prisonId, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Ошибка валидации. Пожалуйста, проверьте введенные данные.");
            return "addGang";
        }
        if (gangRepository.existsByPrisonId(prisonId)) {
            model.addAttribute("errorMessage", "Группировка с таким prison_id уже существует.");
            return "addGang";
        }
        if (gangRepository.existsByLeader(gang.getLeader())) {
            model.addAttribute("errorMessage", "Лидер с таким именем уже существует.");
            return "addGang";
        }
        Prison prison = prisonRepository.findById(prisonId).orElse(null);
        gang.setPrison(prison);
        gangRepository.save(gang);
        return "redirect:/AllGang";
    }


}
