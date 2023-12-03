package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import taba.dajoba.domain.Certificate;
import taba.dajoba.domain.User;
import taba.dajoba.service.CertificateService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    //등록가능한 자격증 정보 전달 -> DB에 수동저장된 데이터
    @GetMapping("users/skill")
    @ResponseBody
    public List<Certificate> loadAllCertificate(){
        List<Certificate> allSkills = certificateService.findAllSkills();
        return allSkills;
    }

    //유저 자격정보 조회
    @GetMapping("users/{userid}/skill")
    @ResponseBody
    public List<Certificate> loadUserCertificate(@PathVariable String userid){
        List<Certificate> userSkills = certificateService.findUserSkills(userid);
        return userSkills;
    }

    //유저 자격정보 등록 및 수정
//    @PostMapping("users/{userid}/skill")
//    @ResponseBody
//    public String updateUserCertificate( @PathVariable String userid , String certificateName  ){
//        User user = certificateService.updateSkills( userid, certificateName );
//        if(user != null){
//            return "SUCCESS";
//        } else {
//            return "FAIL";
//        }
//    }

}
