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
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class editController {

    private final ArticleRepository articleRepository;
    private final CasteRepository casteRepository;
    private final GangRepository gangRepository;
    private final MemberRepository memberRepository;
    private final PrisonRepository prisonRepository;

    // Patch
    @GetMapping("/editMember/{id}")
    public String showEditMemberForm(@PathVariable Long id, Model model) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + id));
        List<Prison> prisons = prisonRepository.findAll();
        List<Caste> castes = casteRepository.findAll();
        List<Gang> gangs = gangRepository.findAll();
        model.addAttribute("member", member);
        model.addAttribute("prisons", prisons);
        model.addAttribute("castes", castes);
        model.addAttribute("gangs", gangs);
        return "editMember";
    }

    @PostMapping("/editMember/{id}")
    public String updateMember(@PathVariable Long id,
                               @ModelAttribute Member member,
                               @RequestParam(value = "prisonId", required = false) Long prisonId,
                               @RequestParam(value = "casteId", required = false) Long casteId,
                               @RequestParam(value = "gangId", required = false) Long gangId,
                               @RequestParam("articleNumber") String articleNumber,
                               @RequestParam("articleDescription") String articleDescription, Model model) {
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
        member.setActive(true);
        member.setArticleNumber(articleNumber);
        member.setArticleDescription(articleDescription);
        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/editCaste/{id}")
    public String showEditCasteForm(@PathVariable Long id, Model model) {
        Caste caste = casteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid caste Id:" + id));
        model.addAttribute("caste", caste);
        return "editCaste";
    }

    @PostMapping("/editCaste/{id}")
    public String editCaste(@PathVariable Long id, @ModelAttribute Caste caste) {
        caste.setId(id);
        casteRepository.save(caste);
        return "redirect:/";
    }

    @GetMapping("/editGang/{id}")
    public String showEditGangForm(@PathVariable Long id, Model model) {
        Gang gang = gangRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid gang Id:" + id));
        List<Prison> prisons = prisonRepository.findAll();
        model.addAttribute("gang", gang);
        model.addAttribute("prisons", prisons);
        return "editGang";
    }

    @PostMapping("/editGang/{id}")
    public String editGang(@PathVariable Long id, @ModelAttribute Gang gang, @RequestParam("prisonId") Long prisonId, Model model) {
        Prison prison = prisonRepository.findById(prisonId).orElse(null);
        gang.setPrison(prison);
        gangRepository.save(gang);
        return "redirect:/AllGang";
    }

    @GetMapping("/editArticle/{id}")
    public String showEditArticleForm(@PathVariable Long id, Model model) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
        model.addAttribute("article", article);
        return "editArticle";
    }

    @PostMapping("/editArticle/{id}")
    public String editArticle(@PathVariable Long id, @ModelAttribute Article article) {
        Article existingArticle = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));

        // Обновление информации о статье для всех заключенных, связанных с этой статьей
        updateMembersArticleInfo(existingArticle, article);

        article.setId(id);
        articleRepository.save(article);

        return "redirect:/";
    }

    private void updateMembersArticleInfo(Article existingArticle, Article updatedArticle) {
        List<Member> members = memberRepository.findByArticleNumber(existingArticle.getName());
        for (Member member : members) {
            member.setArticleNumber(updatedArticle.getName());
            member.setArticleDescription(updatedArticle.getDescription());
            memberRepository.save(member);
        }
    }

    @GetMapping("/editPrison/{id}")
    public String showEditPrisonForm(@PathVariable Long id, Model model) {
        Prison prison = prisonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid prison Id:" + id));
        model.addAttribute("prison", prison);
        return "editPrison";
    }

    @PostMapping("/editPrison/{id}")
    public String editPrison(@PathVariable Long id, @ModelAttribute Prison prison) {
        prison.setId(id);
        prisonRepository.save(prison);
        return "redirect:/";
    }
}
