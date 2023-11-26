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

    @Column(name = "recruit_deadline")
    private Date deadline;

    @Column(name = "working_area")
    private String area;

    @ManyToOne
    @JoinColumn(name = "employment_type_id")
    private EmploymentType employmentType;

    @Column(name = "job_posting_url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "academic_background_id")
    private AcademicBackground academicBackground;

    @OneToOne(mappedBy = "jobPosting")
    private JobPostingDetail jobPostingDetail;

    @OneToMany(mappedBy = "jobPosting")
    private List<Match> matches;

    @Enumerated(EnumType.STRING)
    private JobPostingGroup group;  // PERIODIC FREQUENT
}
