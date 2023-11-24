package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name ="job_posting_detail")
@Getter
public class JobPostingDetail {
    @Id @GeneratedValue
    @Column(name = "job_posting_id")
    private Long id;

    @Column(name = "group_intro")
    private String intro;

    private String mainduties;
    private String qualification;
    private String preferential;

    @OneToOne
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;
}
