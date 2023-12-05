package taba.dajoba.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.domain.Match;
import taba.dajoba.domain.SelfIntroduction;
import taba.dajoba.repository.MatchRepository;
import taba.dajoba.repository.SelfIntroRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final SelfIntroRepository selfIntrodRepository;

    //해당 자소서관련 매칭정보 불러오기
    public List<Match> findMatches (Long selfIntroId){
        SelfIntroduction one = selfIntrodRepository.findOne(selfIntroId);
        if( one.getSignal() == 0 ){ //자소서 분석완료된 상태
            List<Match> matches = matchRepository.showAll(selfIntroId);
            return matches;
        } else {                    //자소서 분석완료되지 않은 상태
            return null;
        }
    }
}
