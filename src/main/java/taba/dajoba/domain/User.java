package taba.dajoba.domain;

import lombok.Getter;
import taba.dajoba.controller.UserExtraInfo;
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

    private int experience = -1;

    @Embedded
    private DesireRegion desireRegion;

    @Enumerated(EnumType.STRING)
    private AcademicBackgroundGroup academicBackground;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserSkill> userSkills = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
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

    //Extra정보(필터링정보) 추가하는 메서드
    public User addExtraInfo(UserExtraInfo userExtraInfo){
        this.academicBackground = userExtraInfo.getAcademicBackground();
        this.experience = userExtraInfo.getExperience();
        this.desireRegion = new DesireRegion(userExtraInfo.getDesireProvince(), userExtraInfo.getDesireCity());
        return this; //정보 추가한 user반환
    }

    //Extra정보(필터링정보) 추출하는 메서드
    public UserExtraInfo loadExtraInfo(){
        UserExtraInfo extraInfo = new UserExtraInfo();
        //입력한 추가정보를 가지고 있다면 객체에 추가
        if(this.academicBackground != null){
            extraInfo.setAcademicBackground(this.academicBackground);
        }
        if(this.experience != -1){
            extraInfo.setExperience(this.experience);
        } else {
            extraInfo.setExperience(-1);
        }
        if( this.desireRegion != null && !this.desireRegion.getDesireProvince().equals("")){
            extraInfo.setDesireProvince(this.desireRegion.getDesireProvince());
        }
        if( this.desireRegion != null && !this.desireRegion.getDesireCity().equals("")){
            extraInfo.setDesireCity(this.desireRegion.getDesireCity());
        }
        return extraInfo;//추출한 userinfo정보 반환
    }

}