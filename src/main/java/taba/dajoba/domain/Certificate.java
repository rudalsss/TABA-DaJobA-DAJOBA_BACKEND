package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Certificate {
    @Id
    @Column(name = "skill_id")
    private Long id;

    private String skillName;

    @OneToMany(mappedBy = "certificate", fetch = FetchType.LAZY)
    private List<UserSkill> userSkills = new ArrayList<>();
}
