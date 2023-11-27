package taba.dajoba.domain;

import lombok.Getter;
import taba.dajoba.controller.UserDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dajoba_user")
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name="user_name_id", unique = true)
    private String userId;

    private String password;

    private String name;

    private String nickname;

    private String birth;

    private String phoneNumber;

    private String email;

    private int experience;

    @Embedded
    private DesireRegion desireRegion;

    private String jobContent;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserSkill> userSkills = new ArrayList<>();


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<SelfIntroduction> selfIntroductions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_background_id")
    private AcademicBackground academicBackground;

    //DTO-> Entity 변환메서드
    public static User toUserEntity (UserDTO userDTO){
        User user = new User();
        user.id = userDTO.getId();
        user.password = userDTO.getPassword();
        user.name = userDTO.getName();
        user.nickname = userDTO.getNickname();
        user.birth = userDTO.getBirth();
        user.phoneNumber = userDTO.getPhoneNumber();
        user.email = userDTO.getEmail();
        return user;
    }

    //Entity-> DTO 변환메서드
    public static UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.id);
        userDTO.setUserId(user.userId);
        userDTO.setPassword(user.password);
        userDTO.setName(user.name);
        userDTO.setNickname(user.nickname);
        userDTO.setBirth(user.birth);
        userDTO.setPhoneNumber(user.phoneNumber);
        userDTO.setEmail(user.email);
        return userDTO;
    }
}