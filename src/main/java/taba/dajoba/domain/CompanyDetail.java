package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name ="company_detail")
@Getter
public class CompanyDetail {
    @Id
    @Column(name = "company_id")
    private Long id;

    private int headcount;

    private Long avgSalary;

    private String companyAddress;

    private Timestamp establishment;

    private String mainBusiness;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_type_id")
    private CompanyType companyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pass_intro_id")
    private PassIntroduction passIntroduction;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}
