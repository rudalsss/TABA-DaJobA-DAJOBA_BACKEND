package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name ="job_posting_detail")
@Getter
public class JobPostingDetail {
    @Id
    @Column(name = "job_posting_id")
    private Long id;

    @Column(name = "group_intro") @Lob
    private String intro;

    @Lob
    private String mainduties;

    @Lob
    private String qualification;

    @Lob
    private String preferential;

    @OneToOne
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;
}
