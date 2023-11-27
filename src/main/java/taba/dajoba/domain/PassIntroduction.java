package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class PassIntroduction {
    @Id
    @Column(name = "pass_intro_id")
    private Long id;

    @Lob
    private String passIntro;

    @OneToMany(mappedBy = "passIntroduction", fetch = FetchType.LAZY)
    private List<CompanyDetail> companyDetails = new ArrayList<>();
}
