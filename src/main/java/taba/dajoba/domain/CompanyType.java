package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company_type")
@Getter
public class CompanyType {
    @Id
    @Column(name = "company_type_id")
    private Long id;

    private String companyTypeContent;

    @OneToMany(mappedBy = "companyType", fetch = FetchType.LAZY)
    private List<CompanyDetail> companyDetails = new ArrayList<>();
}
