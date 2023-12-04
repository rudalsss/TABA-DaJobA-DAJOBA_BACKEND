package taba.dajoba.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taba.dajoba.domain.Match;
import taba.dajoba.domain.QMatch;
import taba.dajoba.domain.QSelfIntroduction;
import taba.dajoba.domain.SelfIntroduction;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MatchRepository {
    private final EntityManager em;

    //==매칭 정보 저장==//
    public void save(Match match) {
        em.persist(match);
    }

    //==매칭 정보 하나 삭제==//
    public void delete(Match match) {
        em.remove(match);
    }


    //==특정 user의 자소서 전부 조회==//
    public List<Match> showAll(Long selfIntroId) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QSelfIntroduction selfIntro = QSelfIntroduction.selfIntroduction;
        QMatch match = QMatch.match;
        return query
                .select(match)
                .from(match)
                .join(selfIntro)
                .on(match.selfIntroduction.eq(selfIntro))
                .where(match.selfIntroduction.id.eq(selfIntroId))
                .fetch();
    }


}
