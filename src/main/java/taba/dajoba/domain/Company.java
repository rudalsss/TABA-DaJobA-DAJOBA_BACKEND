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

    private String companyName;

    @Lob
    private String companyIntro;

    @OneToOne(mappedBy = "company", fetch = FetchType.LAZY)
    private CompanyDetail companyDetail;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<JobPosting> jobPostings = new ArrayList<>();

}