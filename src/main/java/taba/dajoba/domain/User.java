package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DaJobA_USER")
@Getter
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private int experience;

    @Column(name = "job_content")
    private String content;

    @OneToMany(mappedBy = "user")
    private List<UserSkill> userSkills = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Match> matches = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<SelfIntroduction> selfIntroductions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "academic_background_id")
    private AcademicBackground academicBackground;
}
