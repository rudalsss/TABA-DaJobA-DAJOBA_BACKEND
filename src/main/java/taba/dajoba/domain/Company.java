package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SequenceGenerator(
        name="company_seq", sequenceName = "company_seq", initialValue = 1, allocationSize = 1
)
public class Company {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "company_seq")
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