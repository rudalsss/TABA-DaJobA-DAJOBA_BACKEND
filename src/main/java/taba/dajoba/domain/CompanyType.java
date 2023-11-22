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
    @GeneratedValue
    @Column(name = "company_type_id")
    private Long id;

    @Column(name = "company_type_content")
    private String content;

    @OneToMany(mappedBy = "companyType")
    private List<CompanyDetail> companyDetails = new ArrayList<>();
}
