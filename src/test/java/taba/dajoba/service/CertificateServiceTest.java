package taba.dajoba.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.controller.UserForm;
import taba.dajoba.domain.Certificate;
import taba.dajoba.domain.User;
import taba.dajoba.repository.CertificateRepository;
import taba.dajoba.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CertificateServiceTest {

    @Autowired CertificateService certificateService;
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired CertificateRepository certificateRepository;
    
    @Test
    public void 자격증_전체_조회() throws Exception{
        //given
        List<Certificate> list = new ArrayList<>();
        list.add(Certificate.createCertificate("정보처리기사"));
        list.add(Certificate.createCertificate("운전면허증"));
        list.add(Certificate.createCertificate("SQLD"));
        for( Certificate c : list ){
            certificateRepository.saveCertificate(c);
        }

        //when
        List<Certificate> allSkills = certificateService.findAllSkills();

        //then
        for(int i=0; i< list.size();i++){
            assertEquals(list.get(i).getSkillName(), allSkills.get(i).getSkillName());
        }
    }

    @Test
    public void 유저_자격증정보_입력() throws Exception{
        //given
        //유저등록
        UserForm userForm = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        Long savedSeq = userService.join(userForm);
        String userId = userRepository.findOne(savedSeq).getUserId(); //로그인후 세션에 저장할 userid
        //자격증 등록
        List<Certificate> Clist = new ArrayList<>();
        Clist.add(Certificate.createCertificate("자격증1"));
        Clist.add(Certificate.createCertificate("자격증2"));
        Clist.add(Certificate.createCertificate("자격증3"));
        Clist.add(Certificate.createCertificate("자격증4"));
        for( Certificate c :  Clist ){
            certificateRepository.saveCertificate(c);
        }

        //when
        //유저의 자격증 리스트 : 체크리스트로 받음
        List<String> userClist = new ArrayList<>();
        userClist.add( Clist.get(0).getSkillName() );
        userClist.add( Clist.get(2).getSkillName() );
        User skilledUser = certificateService.updateSkills(userId, userClist);

        //then
        for(int i=0; i< skilledUser.getUserSkills().size(); i++){
            assertEquals( skilledUser.getUserSkills().get(i).getCertificate().getSkillName(), userClist.get(i) );
        }
    }

    @Test
    public void 유저_자격증정보_조회() throws Exception{
        //given
        //유저등록
        UserForm userForm = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        Long savedSeq = userService.join(userForm);
        String userId = userRepository.findOne(savedSeq).getUserId(); //로그인후 세션에 저장할 userid
        //자격증 등록
        List<Certificate> Clist = new ArrayList<>();
        Clist.add(Certificate.createCertificate("자격증1"));
        Clist.add(Certificate.createCertificate("자격증2"));
        Clist.add(Certificate.createCertificate("자격증3"));
        Clist.add(Certificate.createCertificate("자격증4"));
        for( Certificate c :  Clist ){
            certificateRepository.saveCertificate(c);
        }

        //when
        //유저의 자격증 리스트 : 체크리스트로 받음
        List<String> userClist = new ArrayList<>();
        userClist.add( Clist.get(0).getSkillName() );
        userClist.add( Clist.get(2).getSkillName() );
        User skilledUser = certificateService.updateSkills(userId, userClist);

        //then
        List<Certificate> findSkills = certificateService.findUserSkills(userId); //조회결과
        for(int i=0; i< skilledUser.getUserSkills().size(); i++){
            //assertEquals( skilledUser.getUserSkills().get(i).getCertificate().getSkillName(), userClist.get(i) );
            assertEquals( skilledUser.getUserSkills().get(i).getCertificate().getSkillName(),
                    findSkills.get(i).getSkillName() );
        }
    }

    @Test
    public void 유저_자격증_수정() throws Exception{
        //given
        //유저등록
        UserForm userForm = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        Long savedSeq = userService.join(userForm);
        String userId = userRepository.findOne(savedSeq).getUserId(); //로그인후 세션에 저장할 userid
        //자격증 등록
        List<Certificate> Clist = new ArrayList<>();
        Clist.add(Certificate.createCertificate("자격증1"));
        Clist.add(Certificate.createCertificate("자격증2"));
        Clist.add(Certificate.createCertificate("자격증3"));
        Clist.add(Certificate.createCertificate("자격증4"));
        for( Certificate c :  Clist ){
            certificateRepository.saveCertificate(c);
        }

        //when
        //유저의 자격증 리스트 : 체크리스트로 받음
        List<String> userClist1 = new ArrayList<>();
        userClist1.add( Clist.get(0).getSkillName() );
        userClist1.add( Clist.get(1).getSkillName() );
        certificateService.updateSkills(userId, userClist1); //첫 등록

        List<String> userClist2 = new ArrayList<>();
        userClist2.add( Clist.get(2).getSkillName() );
        userClist2.add( Clist.get(3).getSkillName() );
        User skilledUser = certificateService.updateSkills(userId, userClist2); //수정

        //then
        //List<Certificate> userSkills = certificateService.findUserSkills(userId);
        for(int i=0; i<skilledUser.getUserSkills().size(); i++){
            assertEquals( skilledUser.getUserSkills().get(i).getCertificate().getSkillName(),
                    userClist2.get(i) );
            assertNotEquals( skilledUser.getUserSkills().get(i).getCertificate().getSkillName(), userClist1.get(i) );
        }
        }
    }