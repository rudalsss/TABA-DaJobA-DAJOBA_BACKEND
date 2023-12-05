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
    @GetMapping("users/{userid}/self-intro")
    public String moveUserSelfIntro(@PathVariable String userid, Model model){
        List<SelfIntroduction> selfIntroductions = selfIntroService.showIntroAll(userid);
        model.addAttribute("selfIntroductions", selfIntroductions);
        return "꾸밀페이지";
    }

    //자기소개서 작성 페이지로 이동
    @GetMapping("users/{userid}/self-intro/new")
    public String createSelfIntroForm(@ModelAttribute("selfIntroForm") SelfIntroForm selfIntroForm){
        return "어떤 url 넣을지 모르겠음";
    }

    //자기소개서 작성
    @PostMapping("users/{userid}/self-intro/new")
    public String createSelfIntro(@ModelAttribute("selfIntroForm") SelfIntroForm form, @PathVariable String userid) {

        SelfIntroduction selfIntroduction = selfIntroService.selfIntro(userid, form.getIntroName(), form.getIntroContent(), form.getDesireField());
        Long id = selfIntroduction.getId();
        return "users/{userid}/self-intro/"+id;
    }

    //자기소개서 상세 조회
    @GetMapping("users/{userid}/self-intro/{introid}")
    public String showSelfIntroDetail(@PathVariable String userid, @PathVariable Long introid, Model model){
        SelfIntroduction selfIntroduction = selfIntroService.showOne(introid);
        model.addAttribute("selfIntroduction", selfIntroduction);
        return "어떤 URL";
    }

    //자기소개서 수정
    @PostMapping("users/{userid}/self-intro/{introid}") @ResponseBody
    public String updateSelfIntro(@ModelAttribute("selfIntroForm") SelfIntroForm form, @PathVariable String userid, @PathVariable Long introid) {
        try {
            //자기소개서 업데이트
            selfIntroService.updateSelfIntro(userid, introid, form.getIntroName(), form.getIntroContent(), form.getDesireField());
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
