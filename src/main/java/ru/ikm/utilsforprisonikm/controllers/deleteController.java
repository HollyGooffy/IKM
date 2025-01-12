package ru.ikm.utilsforprisonikm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ikm.utilsforprisonikm.entity.Article;
import ru.ikm.utilsforprisonikm.entity.Gang;
import ru.ikm.utilsforprisonikm.entity.Member;
import ru.ikm.utilsforprisonikm.repository.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class deleteController {

    private final ArticleRepository articleRepository;
    private final CasteRepository casteRepository;
    private final GangRepository gangRepository;
    private final MemberRepository memberRepository;
    private final PrisonRepository prisonRepository;

    // Delete
    @PostMapping("/deleteMember/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/deleteCaste/{id}")
    public String deleteCaste(@PathVariable Long id) {
        List<Member> members = memberRepository.findByCasteId(id);
        memberRepository.deleteAll(members);
        casteRepository.deleteById(id);

        return "redirect:/AllCaste";
    }

    @PostMapping("/deletePrison/{id}")
    public String deletePrison(@PathVariable Long id) {
        List<Member> members = memberRepository.findByPrisonId(id);
        memberRepository.deleteAll(members);
        List<Gang> gangs = gangRepository.findByPrisonId(id);
        gangRepository.deleteAll(gangs);
        prisonRepository.deleteById(id);

        return "redirect:/AllPrison";
    }

    @PostMapping("/deleteGang/{id}")
    public String deleteGang(@PathVariable Long id) {
        List<Member> members = memberRepository.findByGangId(id);
        memberRepository.deleteAll(members);
        gangRepository.deleteById(id);
        return "redirect:/AllGang";
    }

    @PostMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
        List<Member> members = memberRepository.findByArticleNumber(article.getName());
        memberRepository.deleteAll(members);
        articleRepository.deleteById(id);
        return "redirect:/AllArticle";
    }
}
