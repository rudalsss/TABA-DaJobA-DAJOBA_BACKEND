package taba.dajoba.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserForm {
    private String userId;
    private String password;
    private String name;
    private String nickname;
    private String birth;
    private String phoneNumber;
    private String email;

}