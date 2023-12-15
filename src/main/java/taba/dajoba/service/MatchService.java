package taba.dajoba.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.controller.MatchForm;
import taba.dajoba.domain.JobPosting;
import taba.dajoba.domain.Match;
import taba.dajoba.domain.SelfIntroduction;
import taba.dajoba.domain.User;
import taba.dajoba.repository.JobPostingRepository;
import taba.dajoba.repository.MatchRepository;
import taba.dajoba.repository.SelfIntroRepository;
import taba.dajoba.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchService {

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final SelfIntroRepository selfIntrodRepository;
    private final JobPostingRepository jobPostingRepository;

    //해당 자소서관련 매칭정보 불러오기
    public List<MatchForm> findMatches (Long selfIntroId){
        List<Match> matches = matchRepository.showAll(selfIntroId);
        List<MatchForm> matchForms = matches.stream()
                .map(Match::toMatchForm)
                .collect(Collectors.toList());
        return matchForms;
    }
}
