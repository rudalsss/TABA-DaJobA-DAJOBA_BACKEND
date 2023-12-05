package taba.dajoba.domain;

import lombok.Getter;
import taba.dajoba.controller.JobPostingFilter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name ="job_posting_detail")
@Getter
@SequenceGenerator(
        name="job_posting_detail_seq", sequenceName = "job_posting_detail_seq", initialValue = 1, allocationSize = 1
)
public class JobPostingDetail {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "job_posting_detail_seq")
    @Column(name = "job_posting_id")
    private Long id;

    @Lob
    private String groupIntro;

    @Lob
    private String mainduties;

    @Lob
    private String qualification;

    @Lob
    private String preferential;

    private String

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

}
