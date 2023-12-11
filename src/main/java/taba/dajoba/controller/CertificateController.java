package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba.dajoba.domain.Certificate;
import taba.dajoba.domain.User;
import taba.dajoba.service.CertificateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    // 등록 가능한 자격증 정보 제공 -> DB에 수동 저장된 데이터
    @GetMapping("users/skill")
    public List<Certificate> loadAllCertificate(){
        return certificateService.findAllSkills();
    }

    // 사용자 자격 정보 조회
    @GetMapping("users/{userid}/skill")
    public List<Certificate> loadUserCertificate(@PathVariable String userid){
        return certificateService.findUserSkills(userid);
    }

    // 사용자 자격 정보 등록 및 수정
    @PostMapping("users/{userid}/skill")
    public String updateUserCertificate( @PathVariable String userid , List<String> certificateName  ){
        User user = certificateService.updateSkills( userid, certificateName );
        return (user != null) ? "SUCCESS" : "FAIL";
    }
}
