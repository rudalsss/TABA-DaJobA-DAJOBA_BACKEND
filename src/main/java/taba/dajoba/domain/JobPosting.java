package taba.dajoba.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import taba.dajoba.controller.JobPostingFilter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name ="job_posting")
@Getter
@SequenceGenerator(
        name="job_posting_seq", sequenceName = "job_posting_seq", initialValue = 1, allocationSize = 1
)
public class JobPosting {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "job_posting_seq")
    @Column(name = "job_posting_id")
    private Long id;

    private String title;

    private Date recruitDeadline;

    private String workingArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employment_type_id")
    private EmploymentType employmentType;

    @Enumerated(EnumType.STRING)
    private AcademicBackgroundGroup academicBackground;

    private String jobPostingUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "jobPosting", fetch = FetchType.LAZY)
    private JobPostingDetail jobPostingDetail;

    @OneToMany(mappedBy = "jobPosting")
    private List<Match> matches;

    @Enumerated(EnumType.STRING)
    private JobPostingGroup group;  // PERIODIC FREQUENT

}
