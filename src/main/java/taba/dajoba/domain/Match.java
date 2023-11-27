package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name ="match")
@Getter
@SequenceGenerator(
        name="match_seq", sequenceName = "match_seq", initialValue = 1, allocationSize = 1
)
public class Match {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "match_seq")
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
