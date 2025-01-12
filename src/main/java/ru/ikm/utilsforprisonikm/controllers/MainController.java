package ru.ikm.utilsforprisonikm.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ikm.utilsforprisonikm.entity.*;
import ru.ikm.utilsforprisonikm.repository.*;
import java.util.Comparator;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class MainController {
    private final ArticleRepository articleRepository;
    private final CasteRepository casteRepository;
    private final GangRepository gangRepository;
    private final MemberRepository memberRepository;
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
        List<Article> articles = articleRepository.findAll();

        long memberCount = memberRepository.count();
        long casteCount = casteRepository.count();
        long prisonCount = prisonRepository.count();
        long gangCount = gangRepository.count();

        model.addAttribute("members", members);
        model.addAttribute("castes", castes);
        model.addAttribute("prisons", prisons);
        model.addAttribute("gangs", gangs);
        model.addAttribute("articles", articles);
        model.addAttribute("memberCount", memberCount);
        model.addAttribute("casteCount", casteCount);
        model.addAttribute("prisonCount", prisonCount);
        model.addAttribute("gangCount", gangCount);

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


    @GetMapping("/AllArticle")
    public String showAllArticles(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "AllArticle";
    }
}
