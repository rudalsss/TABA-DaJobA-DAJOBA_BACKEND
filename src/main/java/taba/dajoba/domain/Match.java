package taba.dajoba.domain;

import lombok.Getter;
import taba.dajoba.controller.MatchForm;
import taba.dajoba.controller.UserForm;

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

    @Column(columnDefinition = "NUMBER")
    private double matchScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intro_id")
    private SelfIntroduction selfIntroduction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    //Entity-> Form 변환메서드
    public static MatchForm toMatchForm(Match match){
        MatchForm matchForm = new MatchForm();
        matchForm.setUserName(match.getSelfIntroduction().getUser().getName());
        matchForm.setIntroName(match.getSelfIntroduction().getIntroName());
        matchForm.setMatchScore(match.getMatchScore());
        matchForm.setCompanyName(match.getJobPosting().getCompany());
        matchForm.setJobPostingTitle(match.getJobPosting().getTitle());
        matchForm.setJobPostingId(match.getJobPosting().getId());
        return matchForm;
    }

}
