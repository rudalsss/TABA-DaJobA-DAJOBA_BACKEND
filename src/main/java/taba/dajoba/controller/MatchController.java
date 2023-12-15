package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba.dajoba.domain.Match;
import taba.dajoba.service.MatchService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("users/{userid}/match/{introid}")
    public Map<String, Object> loadMatches(
            @PathVariable Long introid){
        List<MatchForm> matches = matchService.findMatches(introid);
        Map<String, Object> response = new HashMap<>();
        if( matches != null ){
            response.put("matchLists", matches);
        } else {
            response.put("loading", "자소서를 분석중입니다...");
        }
        return response;
    }
}
