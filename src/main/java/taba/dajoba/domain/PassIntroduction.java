package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SequenceGenerator(
        name="pass_introduction_seq", sequenceName = "pass_introduction_seq", initialValue = 1, allocationSize = 1
)
public class PassIntroduction {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "pass_introduction_seq")
    @Column(name = "pass_intro_id")
    private Long id;

    @Lob
    private String passIntro;

    @OneToMany(mappedBy = "passIntroduction", fetch = FetchType.LAZY)
    private List<CompanyDetail> companyDetails = new ArrayList<>();
}
