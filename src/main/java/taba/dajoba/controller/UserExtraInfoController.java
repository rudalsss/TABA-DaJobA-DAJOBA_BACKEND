package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba.dajoba.domain.User;
import taba.dajoba.service.UserExtraInfoService;
import taba.dajoba.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserExtraInfoController {

    private final UserService userService;
    private final UserExtraInfoService userExtraInfoService;

    /**
     * 추가정보(필터정보) 불러오기
     */
    @GetMapping("/users/{userid}/extrainfo")
    public UserExtraInfo loadExtra(@PathVariable String userid){
        return userExtraInfoService.findInfo(userid);
    }

    /**
     * 추가정보(필터정보) 저장 및 수정
     */
    @PostMapping("/users/{userid}/extrainfo")
    public String saveExtra(@PathVariable String userid, @ModelAttribute UserExtraInfo extraInfo){
        User user = userExtraInfoService.updateInfo(userid, extraInfo);
        if(user != null){
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }
}
