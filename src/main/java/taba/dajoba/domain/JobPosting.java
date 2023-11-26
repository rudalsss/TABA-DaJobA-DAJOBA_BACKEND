package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name ="job_posting")
@Getter
public class JobPosting {
    @Id
    @Column(name = "job_posting_id")
    private Long id;

    private String title;

    private Date recruitDeadline;

    private String workingArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employment_type_id")
    private EmploymentType employmentType;

    private String jobPostingUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_background_id")
    private AcademicBackground academicBackground;

    @OneToOne(mappedBy = "jobPosting", fetch = FetchType.LAZY)
    private JobPostingDetail jobPostingDetail;

    @OneToMany(mappedBy = "jobPosting", fetch = FetchType.LAZY)
    private List<Match> matches;

    @Enumerated(EnumType.STRING)
    private JobPostingGroup group;  // PERIODIC FREQUENT
}
