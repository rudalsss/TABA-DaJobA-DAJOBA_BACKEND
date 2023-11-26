package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    //private final UserService userService;

    /**
     * 회원가입회면 이동
     */
    @GetMapping("/signup")
    public String signUpPage(){
        return "/user/signup";
    }

    /**
     * 회원가입처리
     */
    @PostMapping("/signup")
    public String signUp(@ModelAttribute UserDTO userDTO){
        //userService.join(userDTO);
        return "/user/login";
    }

    /**
     * 로그인화면 이동
     */

    /**
     * 로그인처리
     */

    /**
     * 로그아웃
     */



}
