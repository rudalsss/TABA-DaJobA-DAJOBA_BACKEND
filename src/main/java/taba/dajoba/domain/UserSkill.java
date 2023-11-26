package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserSkill {
    @Id
    @Column(name = "user_skill_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Certificate certificate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
