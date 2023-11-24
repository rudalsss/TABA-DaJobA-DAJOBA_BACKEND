package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name ="")
@Getter
public class UserSkill {
    @Id @GeneratedValue
    @Column(name = "user_skill_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Certificate certificate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
