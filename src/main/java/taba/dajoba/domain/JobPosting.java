package taba.dajoba.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import taba.dajoba.controller.JobPostingFilter;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private String jobPostingUrl;

    @Enumerated(EnumType.STRING)
    private Field jobGroup;

    @Enumerated(EnumType.STRING)
    private AcademicBackgroundGroup academicBackground;

    private String groupIntro;

    private String mainduties;

    private String qualification;

    private String preferential;

    private String benefits;

    private String titleIMG;

    private String logoIMG;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "jobPosting")
    private List<Match> matches;

}
