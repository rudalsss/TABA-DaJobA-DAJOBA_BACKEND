package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="pass_introduction")
@Getter
public class PassIntroduction {
    @Id @GeneratedValue
    @Column(name = "pass_introduction_id")
    private Long id;

    @Column(name = "pass_introduction")
    private String introduction;

    @OneToMany(mappedBy = "passIntroduction")
    private List<CompanyDetail> companyDetails = new ArrayList<>();
}
