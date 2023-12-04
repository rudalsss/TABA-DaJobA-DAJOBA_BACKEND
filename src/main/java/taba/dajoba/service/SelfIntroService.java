package taba.dajoba.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.domain.DesireField;
import taba.dajoba.domain.SelfIntroduction;
import taba.dajoba.domain.User;
import taba.dajoba.repository.SelfIntroRepository;
import taba.dajoba.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SelfIntroService {

    private final SelfIntroRepository selfIntroRepository;
    private final UserRepository userRepository;

    /**
     * 자소서
     */
    @Transactional
    public void selfIntro(String userId, String introName, String introContent, DesireField desireField) {
        List<User> users = userRepository.findByUserId(userId);
        User user = users.get(0);
        //String introName, String introContent, User user, DesireField desireField
        SelfIntroduction selfIntroduction = SelfIntroduction.create(introName, introContent, user, desireField);
        selfIntroRepository.save(selfIntroduction);
    }

    /**
     * user 자소서 하나 조회
     */
    public SelfIntroduction showOne(Long introId) {
        return selfIntroRepository.findOne(introId);
    }

    /**
     * user의 자소서 모두 조회
     * 사용법 : String userId = session.getAttribute(loginUser)
     */
    public List<SelfIntroduction> showIntroAll(String userId) {
        return selfIntroRepository.showAll(userId);
    }

    /**
     * user의 자소서 하나 수정
     */
    @Transactional
    public void updateSelfIntro(Long introId, String introName, String introContent, DesireField desireField){
        //selfIntro 조회
        SelfIntroduction selfIntroduction = selfIntroRepository.findOne(introId);
        //자소서 업데이트
        selfIntroduction.update(introName, introContent, desireField);
    }

    /**
     * user 자소서 하나 삭제
     */
    @Transactional
    public void removeSelfIntro(Long introId){
        //selfIntro 조회
        SelfIntroduction selfIntroduction = selfIntroRepository.findOne(introId);
        //자소서 삭제
        selfIntroRepository.delete(selfIntroduction);
    }

    /**
     * IntroName 중복 확인
     */
    public String generateUniqueIntroName(String userId, String introName) {
        List<SelfIntroduction> existingIntros = selfIntroRepository.findByName(userId, introName);
        if (existingIntros.isEmpty()) {
            return introName;
        } else {
            return introName + " (" + existingIntros.size() + ")";
        }
    }

}