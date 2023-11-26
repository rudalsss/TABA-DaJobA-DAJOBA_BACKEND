package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Company {
    @Id
    @Column(name = "company_id")
    private Long id;

    @Column(name = "company_name")
    private String name;

    @Column(name = "company_intro") @Lob
    private String intro;

    @OneToOne(mappedBy = "company")
    private CompanyDetail companyDetail;

    @OneToMany(mappedBy = "company")
    private List<JobPosting> jobPostings = new ArrayList<>();

}