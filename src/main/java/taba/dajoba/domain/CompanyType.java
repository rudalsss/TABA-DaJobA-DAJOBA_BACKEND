package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company_type")
@Getter
@SequenceGenerator(
        name="company_type_seq", sequenceName = "company_type_seq", initialValue = 1, allocationSize = 1
)
public class CompanyType {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "company_type_seq")
    @Column(name = "company_type_id")
    private Long id;

    private String companyTypeContent;

    @OneToMany(mappedBy = "companyType", fetch = FetchType.LAZY)
    private List<CompanyDetail> companyDetails = new ArrayList<>();
}
