package taba.dajoba.controller;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String userId;
    private String password;
    private String name;
    private String nickname;
    private String birth;
    private String phoneNumber;
    private String email;

}