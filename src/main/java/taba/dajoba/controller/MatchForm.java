package taba.dajoba.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MatchForm {
    String userName;// 해당 유저이름 user.name
    String introName;// selfIntroduction.introName
    double matchScore;//매칭 결과 match.matchScore
    String companyName;// 회사 이름 JobPosting.company
    String jobPostingTitle;// 채용 공고 타이틀 JobPosting.title
    Long jobPostingId;// JobPosing.id

}
