package taba.dajoba.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.controller.SelfIntroForm;
import taba.dajoba.controller.SelfIntroMinForm;
import taba.dajoba.domain.SelfIntroduction;
import taba.dajoba.domain.User;
import taba.dajoba.repository.MatchRepository;
import taba.dajoba.repository.SelfIntroRepository;
import taba.dajoba.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SelfIntroService {

    private final SelfIntroRepository selfIntroRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    /**
     * 자소서
     */
    @Transactional
    public Long selfIntro(String userId, SelfIntroForm selfIntroForm) {
        List<User> users = userRepository.findByUserId(userId);
        User user = users.get(0);
        selfIntroForm.setUser(user);
        //자소서 이름 중복확인 후 처리
        selfIntroForm = generateUniqueIntroName(userId, selfIntroForm);
        //자소서 저장
        SelfIntroduction selfIntroduction = SelfIntroduction.toSelfIntroductionEntity(selfIntroForm);
        selfIntroRepository.save(selfIntroduction);
        return selfIntroduction.getId();
    }

    /**
     * 자소서 하나 조회
     */
    public SelfIntroduction showOne(Long introId) {
        return selfIntroRepository.findOne(introId);
    }

    /**
     * user의 자소서 모두 조회
     * 사용법 : String userId = session.getAttribute(loginUser)
     */
    public Page<SelfIntroMinForm> showAllUserSelfIntro(String userId, Pageable pageable){
        return selfIntroRepository.showAllUserSelfIntro(userId, pageable);
    }

    /**
     * user의 자소서 하나 수정
     */
    @Transactional
    public Long updateSelfIntro(String userId, Long introId, SelfIntroForm selfIntroForm) throws Exception {
        //selfIntro 조회
        SelfIntroduction selfIntroduction = selfIntroRepository.findOne(introId);
        if (selfIntroduction == null) {
            throw new Exception("자기소개서를 찾을 수 없습니다.");
        }
        //자소서 이름 중복확인 후 처리
        selfIntroForm = generateUniqueIntroName(userId, selfIntroForm);
        //자소서 업데이트
        boolean isChanged = selfIntroduction.update(selfIntroForm);
        if (isChanged) {
            //해당 자소서 연관된 매칭결과 삭제
            matchRepository.deleteAllRelatedIntroId(introId);
            //signal 값 1로 변경
            selfIntroduction.signalUpdate();
        }
        return selfIntroduction.getId();
    }

    /**
     * user 자소서 하나 삭제
     */
    @Transactional
    public int removeSelfIntro(Long introId){
        //selfIntro 조회
        SelfIntroduction selfIntroduction = selfIntroRepository.findOne(introId);
        //자소서 삭제
        selfIntroRepository.delete(selfIntroduction);
        return 1;
    }

    /**
     * IntroName 중복 확인
     */
    public SelfIntroForm generateUniqueIntroName(String userId, SelfIntroForm form) {
        List<SelfIntroduction> existingIntros = selfIntroRepository.findByName(userId, form.getIntroName());
        if (!existingIntros.isEmpty()) {
            form.setIntroName(form.getIntroName() + " (" + existingIntros.size() + ")");
        }
        return form;
    }

}
