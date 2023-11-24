package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Certificate {
    @Id @GeneratedValue
    @Column(name = "skill_id")
    private Long id;

    @Column(name = "skill_name")
    private String name;

    @OneToMany(mappedBy = "certificate")
    private List<UserSkill> userSkills = new ArrayList<>();
}
