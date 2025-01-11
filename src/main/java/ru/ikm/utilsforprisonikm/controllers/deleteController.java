package ru.ikm.utilsforprisonikm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final NicknameRepository nicknameRepository;
    private final PrisonRepository prisonRepository;

    // Delete
    @PostMapping("/deleteMember/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/deleteCaste/{id}")
    public String deleteCaste(@PathVariable Long id) {
        // Найти всех заключенных, связанных с этой кастой
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

    @PostMapping("/deleteNickname/{id}")
    public String deleteNickname(@PathVariable Long id) {
        nicknameRepository.deleteById(id);
        return "redirect:/AllNickname";
    }

    @PostMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable Long id) {
        List<Member> members = memberRepository.findByArticleNumber(String.valueOf(id));
        memberRepository.deleteAll(members);
        articleRepository.deleteById(id);
        return "redirect:/AllArticle";
    }
}
