package taba.dajoba.domain;

import lombok.Getter;

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
}
