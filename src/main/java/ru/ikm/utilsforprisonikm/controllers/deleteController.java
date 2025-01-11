package ru.ikm.utilsforprisonikm.controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ikm.utilsforprisonikm.entity.Article;
import ru.ikm.utilsforprisonikm.repository.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
        casteRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/deletePrison/{id}")
    public String deletePrison(@PathVariable Long id) {
        prisonRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/deleteGang/{id}")
    public String deleteGang(@PathVariable Long id) {
        gangRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/deleteNickname/{id}")
    public String deleteNickname(@PathVariable Long id) {
        nicknameRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return "redirect:/";
    }
}
