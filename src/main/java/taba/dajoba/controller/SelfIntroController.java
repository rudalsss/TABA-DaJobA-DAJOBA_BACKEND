package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import taba.dajoba.domain.SelfIntroduction;
import taba.dajoba.repository.SelfIntroRepository;
import taba.dajoba.service.SelfIntroService;

@RestController // @Controller -> @RestController 변경
@RequiredArgsConstructor
public class SelfIntroController {

    private final SelfIntroService selfIntroService;
    private final SelfIntroRepository selfIntroRepository;


    //자기소개서 리스트
    @GetMapping("users/{userid}/selfintro/list")
    public Page<SelfIntroMinForm> showUserAllSelfIntro(
            @PathVariable String userid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        return selfIntroService.showAllUserSelfIntro(userid, pageable);
    }


    //자기소개서 작성
    @PostMapping("users/{userid}/selfintro")
    public SelfIntroduction createSelfIntro(@ModelAttribute("selfIntroForm") SelfIntroForm form, @PathVariable String userid) {
        Long id = selfIntroService.selfIntro(userid, form);
        return selfIntroRepository.findOne(id);
    }

    //자기소개서 수정
    @PutMapping("users/{userid}/selfintro/{introid}")
    public String updateSelfIntro(@ModelAttribute("selfIntroForm") SelfIntroForm form, @PathVariable String userid, @PathVariable Long introid) {
        try {
            selfIntroService.updateSelfIntro(userid, introid, form);
            return "success";
        } catch (Exception e) {
            return "fail" + e.getMessage();
        }
    }

    //자기소개서 삭제
    @DeleteMapping("users/{userid}/selfintro/{introid}")
    public String removeSelfIntro(@PathVariable Long introid){
        int result = selfIntroService.removeSelfIntro(introid);
        return result != 1 ? "success" : "fail";
    }
}