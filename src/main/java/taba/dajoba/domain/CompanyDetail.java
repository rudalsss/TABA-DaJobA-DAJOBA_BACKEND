package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name ="company_detail")
@Getter
public class CompanyDetail {
    @Id @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    private int headcount;

    @Column(name = "avg_salary")
    private Long avgSalary;

    @Embedded
    @Column(name = "company_address")
    private String coAddress;

    private Timestamp establishment;

    @Column(name = "main_business")
    private String mainBusiness;

    @ManyToOne
    @JoinColumn(name = "company_type_id")
    private CompanyType companyType;

    @ManyToOne
    @JoinColumn(name = "pass_intro_id")
    private PassIntroduction passIntroduction;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
