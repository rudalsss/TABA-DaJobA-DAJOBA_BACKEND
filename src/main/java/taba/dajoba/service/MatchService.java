package taba.dajoba.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.domain.Match;
import taba.dajoba.domain.SelfIntroduction;
import taba.dajoba.repository.MatchRepository;
import taba.dajoba.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    /**
     * 특정 자소서의 매칭 결과 모두 조회
     */
    public List<Match> showMatchAll(Long introId) {
        return matchRepository.showAll(introId);
    }



}
