package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

        selfIntroService.selfIntro(userid, form.getIntroName(), form.getIntroContent(), form.getDesireField());
        return "어떤url";
    }

    //자기소개서 상세 조회
    @GetMapping("users/{userid}/self-intro/{introid}")
    public String showSelfIntroDetail(@PathVariable String userid, @PathVariable Long introid, Model model){
        SelfIntroduction selfIntroduction = selfIntroService.showOne(introid);
        model.addAttribute("selfIntroduction", selfIntroduction);
        return "어떤 URL";
    }

    //자기소개서 수정
    @PostMapping("users/{userid}/self-intro/{introid}")
    public String updateSelfIntro(@ModelAttribute("selfIntroForm") SelfIntroForm form, @PathVariable String userid, @PathVariable Long introid) {
        selfIntroService.updateSelfIntro(introid, form.getIntroName(), form.getIntroContent(), form.getDesireField());
        return "어떤url";
    }
}
