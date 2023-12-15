package taba.dajoba.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.controller.UserExtraInfo;
import taba.dajoba.controller.UserForm;
import taba.dajoba.domain.AcademicBackgroundGroup;
import taba.dajoba.domain.User;
import taba.dajoba.repository.UserRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserExtraInfoServiceTest {

    @Autowired UserExtraInfoService userExtraInfoService;
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Test
    public void 유저추가정보_입력() throws Exception{
        //given
        UserForm userForm = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        Long savedSeq = userService.join(userForm);

        UserExtraInfo extraInfo= new UserExtraInfo(); //컨트롤러에서 받게되는 유저추가정보
        extraInfo.setAcademicBackground(AcademicBackgroundGroup.BD);
        extraInfo.setExperience(0);
        extraInfo.setDesireProvince("경기도");
        extraInfo.setDesireCity("수원시");

        //when
        String userId = userRepository.findOne(savedSeq).getUserId(); //로그인후 세션에 저장할 userid
        User addedUser = userExtraInfoService.updateInfo(userId, extraInfo);

        //then
        assertEquals(extraInfo.getAcademicBackground(), addedUser.getAcademicBackground());
        assertEquals(extraInfo.getExperience(), addedUser.getExperience());
        assertEquals(extraInfo.getDesireProvince(), addedUser.getDesireRegion().getDesireProvince());
        assertEquals(extraInfo.getDesireCity(), addedUser.getDesireRegion().getDesireCity());
    }

    @Test
    public void 유저추가정보_수정() throws Exception{
        //given
        UserForm userForm = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        Long savedSeq = userService.join(userForm);
        String userId = userRepository.findOne(savedSeq).getUserId(); //로그인후 세션에 저장할 userid

        UserExtraInfo extraInfo1= new UserExtraInfo(); //첫입력
        extraInfo1.setAcademicBackground(AcademicBackgroundGroup.BD);
        extraInfo1.setExperience(0);
        extraInfo1.setDesireProvince("경기도");
        extraInfo1.setDesireCity("수원시");
        User addedUser1 = userExtraInfoService.updateInfo(userId, extraInfo1);

        //when
        UserExtraInfo extraInfo2= new UserExtraInfo(); //수정수행
        extraInfo2.setAcademicBackground(AcademicBackgroundGroup.MD);
        extraInfo2.setExperience(3);
        extraInfo2.setDesireProvince("경기도");
        extraInfo2.setDesireCity("용인시");
        User addedUser2 = userExtraInfoService.updateInfo(userId, extraInfo2);

        //then : 수정된 정보와 일치
        assertEquals(extraInfo2.getAcademicBackground(), addedUser2.getAcademicBackground());
        assertEquals(extraInfo2.getExperience(), addedUser2.getExperience());
        assertEquals(extraInfo2.getDesireProvince(), addedUser2.getDesireRegion().getDesireProvince());
        assertEquals(extraInfo2.getDesireCity(), addedUser2.getDesireRegion().getDesireCity());

    }

    @Test
    public void 유저추가정보_조회() throws Exception{
        //given
        UserForm userForm = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        Long savedSeq = userService.join(userForm);
        String userId = userRepository.findOne(savedSeq).getUserId(); //로그인후 세션에 저장할 userid

        UserExtraInfo extraInfo= new UserExtraInfo(); //컨트롤러에서 받게되는 유저추가정보
        extraInfo.setAcademicBackground(AcademicBackgroundGroup.BD);
        extraInfo.setExperience(0);
        extraInfo.setDesireProvince("충청도");
        extraInfo.setDesireCity("천안시");
        User addedUser = userExtraInfoService.updateInfo(userId, extraInfo);

        //when
        UserExtraInfo loadInfo = userExtraInfoService.findInfo(userId);

        //then
        assertEquals(extraInfo.getAcademicBackground(), loadInfo.getAcademicBackground());
        assertEquals(extraInfo.getExperience(), loadInfo.getExperience());
        assertEquals(extraInfo.getDesireProvince(), loadInfo.getDesireProvince());
        assertEquals(extraInfo.getDesireCity(), loadInfo.getDesireCity());
    }

    @Test
    public void 추가정보없는_유저() throws Exception{
        //given
        UserForm userForm = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        Long savedSeq = userService.join(userForm);
        String userId = userRepository.findOne(savedSeq).getUserId(); //로그인후 세션에 저장할 userid

        //when
        UserExtraInfo loadInfo = userExtraInfoService.findInfo(userId);

        //then
        assertNull(loadInfo.getAcademicBackground());
        assertEquals(loadInfo.getExperience(), -1);
        assertNull(loadInfo.getDesireProvince());
        assertNull(loadInfo.getDesireCity());
    }

}