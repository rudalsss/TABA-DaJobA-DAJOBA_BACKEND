package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taba.dajoba.domain.SelfIntroduction;
import taba.dajoba.service.SelfIntroService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SelfIntroController {

    private final SelfIntroService selfIntroService;

    //자기소개서 목록페이지로 이동
    @GetMapping("users/{userid}/self-intro/list")
    public List<SelfIntroduction> moveUserSelfIntro(@PathVariable String userid){
        return selfIntroService.showIntroAll(userid);
    }

    //자기소개서 작성
    @PostMapping("users/{userid}/self-intro")
    public SelfIntroduction createSelfIntro(@ModelAttribute("selfIntroForm") SelfIntroForm form, @PathVariable String userid) {
        return selfIntroService.selfIntro(userid, form.getIntroName(), form.getIntroContent(), form.getField());
    }

    //자기소개서 상세 조회
    @GetMapping("users/{userid}/self-intro/{introid}")
    public SelfIntroduction showSelfIntroDetail(@PathVariable String userid, @PathVariable Long introid){
        return selfIntroService.showOne(introid);
    }

    //자기소개서 수정
    @PostMapping("users/{userid}/self-intro/{introid}")
    public String updateSelfIntro(@ModelAttribute("selfIntroForm") SelfIntroForm form, @PathVariable String userid, @PathVariable Long introid) {
        try {
            //자기소개서 업데이트
            selfIntroService.updateSelfIntro(userid, introid, form.getIntroName(), form.getIntroContent(), form.getField());
            return "success";
        } catch (Exception e) {
            return "fail" + e.getMessage();
        }
    }
    //자기소개서 삭제
    @DeleteMapping("users/{userid}/self-intro/{introid}") @ResponseBody
    public String removeSelfIntro(@PathVariable Long introid){
        int result = selfIntroService.removeSelfIntro(introid);
        if(result != 1){
            return "success";
        } else {
            return "fail";
        }
    }
}
