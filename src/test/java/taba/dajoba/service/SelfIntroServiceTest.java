package taba.dajoba.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.controller.UserForm;
import taba.dajoba.domain.Field;
import taba.dajoba.domain.SelfIntroduction;
import taba.dajoba.repository.SelfIntroRepository;
import taba.dajoba.repository.UserRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SpringBootTest
@Transactional
class SelfIntroServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired SelfIntroService selfIntroService;
    @Autowired SelfIntroRepository selfIntroRepository;

    @Test
    public void 자기소개서생성() throws Exception {
        //given
        UserForm userForm = new UserForm(
                "유저아이디", "패스워드", "내이름", "닉네임", "1998-06-10",
                "010-0811-0811", "이메일@naver.com"
        );
        Long savedSeq = userService.join(userForm);
        String userId = userRepository.findOne(savedSeq).getUserId();

        String introName = "새로운 자기소개";
        String introContent = "안녕하세요, 저는 새로 가입한 사용자입니다.";
        Field field = Field.BUSINESS;

        //when
        selfIntroService.selfIntro(userId, introName, introContent, field);

        //then
        List<SelfIntroduction> selfIntroductions = selfIntroRepository.showAll(userId);
        SelfIntroduction selfIntroduction = selfIntroductions.get(0);

        assertEquals(introName, selfIntroduction.getIntroName());
        assertEquals(introContent, selfIntroduction.getIntroContent());
        assertEquals(userId, selfIntroduction.getUser().getUserId());
        assertEquals(field, selfIntroduction.getField());
    }

    @Test
    public void User자소서조회 () throws Exception {
        //given
        UserForm userForm = new UserForm(
                "유저아이디", "패스워드", "내이름", "닉네임", "1998-06-10",
                "010-0811-0811", "이메일@naver.com"
        );
        Long savedSeq = userService.join(userForm);
        String userId = userRepository.findOne(savedSeq).getUserId();

        String introName1 = "새로운 자기소개";
        String introContent1 = "안녕하세요, 저는 새로 가입한 사용자입니다.";
        Field field1 = Field.BUSINESS;

        String introName2 = "새로운 자기소개";
        String introContent2 = "안녕하세요, 저는 새로 가입한 사용자입니다.2222";
        Field field2 = Field.BUSINESS;



        //when
        selfIntroService.selfIntro(userId, introName1, introContent1, field1);
        selfIntroService.selfIntro(userId, introName2, introContent2, field2);
        selfIntroService.showIntroAll(userId);

        //then
        List<SelfIntroduction> selfIntroductions = selfIntroRepository.showAll(userId);

        SelfIntroduction selfIntroduction1 = selfIntroductions.get(0);
        SelfIntroduction selfIntroduction2 = selfIntroductions.get(1);
        assertEquals(introName1, selfIntroduction1.getIntroName());
        assertEquals(introContent1, selfIntroduction1.getIntroContent());
        assertEquals(userId, selfIntroduction1.getUser().getUserId());
        assertEquals(field1, selfIntroduction1.getField());
        assertEquals(introName2, selfIntroduction2.getIntroName());
        assertEquals(introContent2, selfIntroduction2.getIntroContent());
        assertEquals(userId, selfIntroduction2.getUser().getUserId());
        assertEquals(field2, selfIntroduction2.getField());
    }

    @Test
    public void 자소서하나삭제() throws Exception {
        //given
        UserForm userForm = new UserForm(
                "유저아이디", "패스워드", "내이름", "닉네임", "1998-06-10",
                "010-0811-0811", "이메일@naver.com"
        );
        Long savedSeq = userService.join(userForm);
        String userId = userRepository.findOne(savedSeq).getUserId();

        String introName1 = "새로운 자기소개";
        String introContent1 = "안녕하세요, 저는 새로 가입한 사용자입니다.";
        Field field1 = Field.BUSINESS;

        String introName2 = "새로운 자기소개";
        String introContent2 = "안녕하세요, 저는 새로 가입한 사용자입니다.2222";
        Field field2 = Field.BUSINESS;

        //when
        selfIntroService.selfIntro(userId, introName1, introContent1, field1);
        selfIntroService.selfIntro(userId, introName2, introContent2, field2);
        List<SelfIntroduction> selfIntroductions = selfIntroRepository.showAll(userId);
        Long id1 = selfIntroductions.get(0).getId();
        selfIntroService.removeSelfIntro(id1);

        //then
        selfIntroductions = selfIntroRepository.showAll(userId);
        SelfIntroduction selfIntroduction = selfIntroductions.get(0);
        assertEquals(introName2+" (1)", selfIntroduction.getIntroName());
        assertEquals(introContent2, selfIntroduction.getIntroContent());
        assertEquals(userId, selfIntroduction.getUser().getUserId());
        assertEquals(field2, selfIntroduction.getField());
    }

    @Test
    public void 자소서수정() throws Exception {
        //given
        UserForm userForm = new UserForm(
                "유저아이디", "패스워드", "내이름", "닉네임", "1998-06-10",
                "010-0811-0811", "이메일@naver.com"
        );
        Long savedSeq = userService.join(userForm);
        String userId = userRepository.findOne(savedSeq).getUserId();

        String introName = "새로운 자기소개";
        String introContent = "안녕하세요, 저는 새로 가입한 사용자입니다.";
        Field field = Field.BUSINESS;
        SelfIntroduction selfIntro = selfIntroService.selfIntro(userId, introName, introContent, field);

        //when
        String fixedName = "수정된 자기소개서";
        String fixedContent = "안녕하세요, 저는 자기소개서 수정입니다.";
        Field fixedDF = Field.SERVICE;
        SelfIntroduction fixedIntro = selfIntroService.updateSelfIntro(userId, selfIntro.getId(),fixedName, fixedContent, fixedDF);

        //then
        assertEquals(fixedName, fixedIntro.getIntroName());
        assertEquals(fixedContent, fixedIntro.getIntroContent());
        assertEquals(fixedDF, fixedIntro.getField());
    }

    @Test
    public void 자소서이름중복확인처리() throws Exception {
        //given
        UserForm userForm = new UserForm(
                "유저아이디", "패스워드", "내이름", "닉네임", "1998-06-10",
                "010-0811-0811", "이메일@naver.com"
        );
        Long savedSeq = userService.join(userForm);
        String userId = userRepository.findOne(savedSeq).getUserId();

        String introName1 = "새로운 자기소개";
        String introContent1 = "안녕하세요, 저는 새로 가입한 사용자입니다.";
        Field field1 = Field.BUSINESS;

        String introName2 = "새로운 자기소개";
        String introContent2 = "안녕하세요, 저는 새로 가입한 사용자입니다.2222";
        Field field2 = Field.BUSINESS;



        //when
        selfIntroService.selfIntro(userId, introName1, introContent1, field1);
        selfIntroService.selfIntro(userId, introName2, introContent2, field2);
        selfIntroService.showIntroAll(userId);

        //then
        List<SelfIntroduction> selfIntroductions = selfIntroRepository.showAll(userId);

        SelfIntroduction selfIntroduction1 = selfIntroductions.get(0);
        SelfIntroduction selfIntroduction2 = selfIntroductions.get(1);
        assertNotEquals(selfIntroduction1.getIntroName(), selfIntroduction2.getIntroName());
        assertEquals("새로운 자기소개 (1)",selfIntroduction2.getIntroName());

    }





}