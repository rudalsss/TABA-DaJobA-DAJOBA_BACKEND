package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="pass_introduction")
@Getter
public class PassIntroduction {
    @Id
    @Column(name = "pass_intro_id")
    private Long id;

    @Column(name = "pass_intro") @Lob
    private String introduction;

    @OneToMany(mappedBy = "passIntroduction")
    private List<CompanyDetail> companyDetails = new ArrayList<>();
}
