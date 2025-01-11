package ru.ikm.utilsforprisonikm.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ikm.utilsforprisonikm.entity.*;
import ru.ikm.utilsforprisonikm.repository.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ArticleRepository articleRepository;
    private final CasteRepository casteRepository;
    private final GangRepository gangRepository;
    private final MemberRepository memberRepository;
    private final NicknameRepository nicknameRepository;
    private final PrisonRepository prisonRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Member> members = memberRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(Member::getId)).toList();

        List<Caste> castes = casteRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(Caste::getId)).toList();

        List<Prison> prisons = prisonRepository.findAll();
        List<Gang> gangs = gangRepository.findAll();
        List<Nickname> nicknames = nicknameRepository.findAll();
        List<Article> articles = articleRepository.findAll();

        model.addAttribute("members", members);
        model.addAttribute("castes", castes);
        model.addAttribute("prisons", prisons);
        model.addAttribute("gangs", gangs);
        model.addAttribute("nicknames", nicknames);
        model.addAttribute("articles", articles);

        return "index";
    }

    @GetMapping("/AllPrison")
    public String showAllPrisons(Model model) {
        List<Prison> prisons = prisonRepository.findAll();
        model.addAttribute("prisons", prisons);
        return "AllPrison";
    }

    @GetMapping("/AllCaste")
    public String showAllCastes(Model model) {
        List<Caste> castes = casteRepository.findAll();
        model.addAttribute("castes", castes);
        return "AllCaste";
    }

    @GetMapping("/AllGang")
    public String showAllGangs(Model model) {
        List<Gang> gangs = gangRepository.findAll();
        model.addAttribute("gangs", gangs);
        return "AllGang";
    }

}
