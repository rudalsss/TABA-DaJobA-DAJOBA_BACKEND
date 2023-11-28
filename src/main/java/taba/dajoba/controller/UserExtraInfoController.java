package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import taba.dajoba.domain.User;
import taba.dajoba.service.UserExtraInfoService;
import taba.dajoba.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserExtraInfoController {

    private final UserService userService;
    private final UserExtraInfoService userExtraInfoService;

    /**
     * 추가정보(필터정보) 불러오기
     */
    @GetMapping("/users/{userid}/extra-info")
    @ResponseBody
    public UserExtraInfo loadExtra(@PathVariable String userid){
        UserExtraInfo userExtraInfo = userExtraInfoService.findInfo(userid);
        return userExtraInfo;
    }

    /**
     * 추가정보(필터정보) 저장 및 수정
     */
    @PostMapping("/users/{userid}/extra-info")
    @ResponseBody
    public String saveExtra(@PathVariable String userid, @ModelAttribute UserExtraInfo extraInfo){
        User user = userExtraInfoService.updateInfo(userid, extraInfo);
        if(user != null){
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

}
