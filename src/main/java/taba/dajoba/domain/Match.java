package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name ="match")
@Getter
public class Match {
    @Id
    @Column(name = "match_id")
    private Long id;

    private int matchScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intro_id")
    private SelfIntroduction selfIntroduction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;
}
