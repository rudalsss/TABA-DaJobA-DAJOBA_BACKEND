package taba.dajoba.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.domain.Certificate;
import taba.dajoba.domain.User;
import taba.dajoba.domain.UserSkill;
import taba.dajoba.repository.CertificateRepository;
import taba.dajoba.repository.UserRepository;
import taba.dajoba.repository.UserSkillRepository;

import java.util.ArrayList;
import java.util.List;

import static taba.dajoba.domain.QUserSkill.userSkill;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CertificateService {

    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;
    private final UserSkillRepository userSkillRepository;


    /**
     * 등록가능한 모든 자격증정보 조회
     */
    public List<Certificate> findAllSkills(){
        return certificateRepository.findAll();
    }

    /**
     * 유저의 자격증 정보 조회
     */
    public List<Certificate> findUserSkills(String userId){
        //유저조회
        List<User> findUsers = userRepository.findByUserId(userId);
        if( !findUsers.isEmpty() ) {
            //userskill조회
            List<UserSkill> userSkills = userSkillRepository.findUserSkill(findUsers.get(0));
            List<Certificate> results = new ArrayList<>();
            for ( UserSkill u : userSkills ){
                results.add(u.getCertificate());
            }
            return results;
        } else {
            return null;
        }

    }


    /**
     * 유저의 자격증 정보 등록, 수정!!!
     */
    @Transactional
    public User updateSkills(String userId, List<String> certificateName){
        //등록할 자격증 리스트
        List<Certificate> certificates = new ArrayList<>();
        for( String name : certificateName ){
            List<Certificate> finds = certificateRepository.findByName(name);
            Certificate certificate = finds.get(0);
            certificates.add(certificate);
        }

        //유저조회
        List<User> findUsers = userRepository.findByUserId(userId);

        if( !findUsers.isEmpty() ){
            //기존 자격 삭제
            List<UserSkill> userSkills = userSkillRepository.findUserSkill(findUsers.get(0));
            for(UserSkill u : userSkills){              //UserSkill엔티티 삭제
                userSkillRepository.removeUserSkill(u);
            }
            findUsers.get(0).getUserSkills().clear();   //User엔티티에서도 삭제

            //새로운 자격 등록
            for( Certificate c : certificates ){
                UserSkill userSkill = UserSkill.addSkill(findUsers.get(0), c); //생성, User에 등록
                userSkillRepository.save(userSkill);                           //UserSkill에 등록
            }
            return findUsers.get(0);
        } else {
            return null;
        }
    }

}
