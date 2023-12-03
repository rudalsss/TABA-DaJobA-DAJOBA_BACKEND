package taba.dajoba.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@SequenceGenerator(
        name="user_skill_seq", sequenceName = "user_skill_seq", initialValue = 1, allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    //추가 메서드
    public static UserSkill addSkill(User user, Certificate certificate){
        UserSkill userSkill = new UserSkill();
        userSkill.certificate = certificate;
        userSkill.user = user;
        user.getUserSkills().add(userSkill);
        return userSkill;
    }

}
