package taba.dajoba.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.controller.UserForm;
import taba.dajoba.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Test
    public void 회원가입() throws Exception{
        //given
        UserForm userForm = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        //when
        Long savedSeq = userService.join(userForm);

        //then
        String savedUserId = userRepository.findOne(savedSeq).getUserId();
        assertEquals(userForm.getUserId(), savedUserId);
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외검사() throws Exception{
        //given
        UserForm user1 = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        UserForm user2 = new UserForm(
                "아이디", "비번2", "이름2", "별명2", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );

        //when
        userService.join(user1);
        userService.join(user2);

        //then
        fail("예외가 나야하는 구간...");
    }

    @Test
    public void 로그인테스트() throws Exception{
        //given
        UserForm signed_user = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        userService.join(signed_user);

        UserForm login_user = new UserForm();
        login_user.setUserId(signed_user.getUserId());
        login_user.setPassword(signed_user.getPassword());

        //when
        UserForm success_user = userService.login(login_user);

        //then
        assertEquals(success_user.getUserId(), login_user.getUserId());
    }

    @Test
    public void 로그인_아이디존재하지않음() throws Exception{
        //given
        UserForm signed_user = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        userService.join(signed_user);

        UserForm login_user = new UserForm();
        login_user.setUserId("없는 아이디");
        login_user.setPassword(signed_user.getPassword());

        //when
        UserForm result_user = userService.login(login_user);

        //then
        assertNull(result_user);
    }

    @Test
    public void 로그인_비밀번호불일치() throws Exception{
        //given
        UserForm signed_user = new UserForm(
                "아이디", "비번", "이름", "별명", "2000-02-28",
                "010-1234-1234", "이메일@gmail.com"
        );
        userService.join(signed_user);

        UserForm login_user = new UserForm();
        login_user.setUserId(signed_user.getUserId());
        login_user.setPassword("불일치하는 아이디");

        //when
        UserForm result_user = userService.login(login_user);

        //then
        assertNull(result_user);
    }

}