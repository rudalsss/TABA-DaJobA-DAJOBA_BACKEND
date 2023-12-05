package taba.dajoba.controller;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import taba.dajoba.domain.Match;
import taba.dajoba.domain.SelfIntroduction;
import taba.dajoba.service.MatchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final String url = "파이썬 서버 주소~~";

    //매칭요청
    @PostMapping("match/{userid}/{introid}")
    @ResponseBody
    public String requestMatch(@PathVariable String userid, @PathVariable Long introid){
        //유저의 자소서 전달
        SelfIntroduction selfIntroduction = matchService.sendInfo(introid);
        //필터링된 채용정보 전달
        List<Tuple> tuples = matchService.sendJobPosting(userid, introid);

        //HTTP요청헤더 설정
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        // 전송할 JSON 데이터 설정
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("selfIntroduction", selfIntroduction);
        jsonData.put("jobPostings", tuples);

        // HttpEntity에 JSON 데이터와 헤더 설정
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(jsonData, httpHeaders);

        // 요청 보내기 (POST 예제)
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        // 응답 받은 데이터
        String responseBody = responseEntity.getBody();
        return responseBody;
    }

    //매칭결과 조회
    @GetMapping("match/{userid}/{introid}")
    public String resultMatch( @PathVariable Long introid, Model model ){
        List<Match> matches = matchService.resultMatching(introid);
        model.addAttribute("matchlist", matches);
        return "매칭결과 페이지";
    }

}
