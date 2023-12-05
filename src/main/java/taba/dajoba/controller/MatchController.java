package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import taba.dajoba.domain.Match;
import taba.dajoba.service.MatchService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("users/{userid}/match/{introid}")
    public String loadMatches( @PathVariable Long introid, Model model ){
        List<Match> matches = matchService.findMatches(introid);
        if( matches != null ){
            model.addAttribute("matchLists", matches);
        } else {
            model.addAttribute("loading", "자소서를 분석중입니다..." );
        }
        return "매칭page";
    }

}
