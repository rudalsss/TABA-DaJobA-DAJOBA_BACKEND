package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name ="user_skill")
@Getter
public class UserSkill {
    @Id
    @Column(name = "user_skill_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Certificate certificate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
