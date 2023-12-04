package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SequenceGenerator(
        name="certificate_seq", sequenceName = "certificate_seq", initialValue = 1, allocationSize = 1
)
public class Certificate {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "certificate_seq")
    @Column(name = "skill_id")
    private Long id;

    private String skillName;
}
