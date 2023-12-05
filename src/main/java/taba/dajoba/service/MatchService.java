package taba.dajoba.service;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.controller.UserExtraInfo;
import taba.dajoba.domain.JobPosting;
import taba.dajoba.domain.Match;
import taba.dajoba.domain.SelfIntroduction;
import taba.dajoba.repository.JobPostingRepository;
import taba.dajoba.repository.MatchRepository;
import taba.dajoba.repository.SelfIntroRepository;
import taba.dajoba.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final SelfIntroRepository selfIntroRepository;
    private final UserRepository userRepository;
    private final UserExtraInfoService userExtraInfoService;
    private final JobPostingRepository jobPostingRepository;

    /**
     * 매칭요청1 : 유저 자소서 전달
     */
    public SelfIntroduction sendInfo( Long infoId ){
        SelfIntroduction one = selfIntroRepository.findOne(infoId);
        return one;
    }

    /**
     * 매칭요청2 : 필터링된 채용정보 조회( 근무지역, 희망직무 )
     */
    public List<Tuple> sendJobPosting( String userId, Long introId ){
        UserExtraInfo info = userExtraInfoService.findInfo(userId); //추가정보 조회
        SelfIntroduction one = selfIntroRepository.findOne(introId); //자소서 조회

        if( info.getDesireProvince()!=null && info.getDesireCity()!= null  ) {
            List<Tuple> tuples = jobPostingRepository.filterPosting(info.getDesireProvince(), info.getDesireCity(), one.getDesireField().toString());
            return tuples;
        } else {
            return null;
            //제어가 필요..
        }

    }

    /**
     * 매칭결과 조회
     */
    public List<Match> resultMatching( Long introId ){
        return matchRepository.showAll(introId);
    }




}
