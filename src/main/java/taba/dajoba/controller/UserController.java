package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba.dajoba.domain.User;
import taba.dajoba.repository.UserRepository;
import taba.dajoba.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * 회원가입처리
     */
    @PostMapping("/signup")
    public User signUp(@ModelAttribute UserForm userForm){
        Long id = userService.join(userForm);
        return userRepository.findOne(id);
    }

    /**
     * 로그인처리
     */
    @PostMapping("/login")
    public User login(@ModelAttribute UserForm userForm, HttpSession session){
        UserForm loginResult = userService.login(userForm);
        if(loginResult != null ){
            //로그인 성공
            session.setAttribute("loginUser", loginResult.getUserId());
            List<User> users = userRepository.findByUserId(loginResult.getUserId());
            return users.get(0);
        } else {
            return null;
        }
    }

    /**
     * 로그아웃처리
     */
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "Logged out";
    }
}
