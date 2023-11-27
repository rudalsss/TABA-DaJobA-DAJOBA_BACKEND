package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@SequenceGenerator(
        name="user_skill_seq", sequenceName = "user_skill_seq", initialValue = 1, allocationSize = 1
)
public class UserSkill {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "user_skill_seq")
    @Column(name = "user_skill_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Certificate certificate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
