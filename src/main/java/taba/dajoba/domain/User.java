package taba.dajoba.domain;

import lombok.Getter;
import taba.dajoba.controller.UserForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dajoba_user")
@Getter
@SequenceGenerator(
        name="dajoba_user_seq", sequenceName = "dajoba_user_seq", initialValue = 1, allocationSize = 1
)
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "dajoba_user_seq")
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

    @Enumerated(EnumType.STRING)
    private AcademicBackgroundGroup academicBackground;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserSkill> userSkills = new ArrayList<>();


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<SelfIntroduction> selfIntroductions = new ArrayList<>();


    //Form-> Entity 변환메서드
    public static User toUserEntity (UserForm userForm){
        User user = new User();
        user.userId = userForm.getUserId();
        user.password = userForm.getPassword();
        user.name = userForm.getName();
        user.nickname = userForm.getNickname();
        user.birth = userForm.getBirth();
        user.phoneNumber = userForm.getPhoneNumber();
        user.email = userForm.getEmail();
        return user;
    }

    //Entity-> Form 변환메서드
    public static UserForm toUserForm(User user){
        UserForm userForm = new UserForm();
        userForm.setUserId(user.userId);
        userForm.setPassword(user.password);
        userForm.setName(user.name);
        userForm.setNickname(user.nickname);
        userForm.setBirth(user.birth);
        userForm.setPhoneNumber(user.phoneNumber);
        userForm.setEmail(user.email);
        return userForm;
    }
}