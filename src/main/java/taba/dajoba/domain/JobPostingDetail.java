package taba.dajoba.domain;

import lombok.Getter;
import taba.dajoba.controller.JobPostingFilter;

import javax.persistence.*;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

//    public JobPostingFilter detailToFilter(JobPosting jobPosting, JobPostingFilter jobPostingFilter){
//        jobPostingFilter.setMainduties(this.mainduties);
//        return jobPostingFilter;
//    }

}
