package taba.dajoba.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import taba.dajoba.controller.JobPostingForm;
import taba.dajoba.controller.SelfIntroMinForm;
import taba.dajoba.domain.QSelfIntroduction;
import taba.dajoba.domain.QUser;
import taba.dajoba.domain.SelfIntroduction;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SelfIntroRepository {
    private final EntityManager em;

    //==자소서 저장==//
    public void save(SelfIntroduction selfIntroduction) {
        em.persist(selfIntroduction);
    }

    //==자소서 하나 삭제==//
    public void delete(SelfIntroduction selfIntroduction) {
        em.remove(selfIntroduction);
    }

    //==자소서 하나 검색==//
    public SelfIntroduction findOne(Long id) {
        return em.find(SelfIntroduction.class, id);
    }

    //==특정 user의 자소서 전부 조회==//
    public Page<SelfIntroMinForm> showAllUserSelfIntro(String userId, Pageable pageable) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QSelfIntroduction selfIntro = QSelfIntroduction.selfIntroduction;
        JPAQuery<SelfIntroMinForm> querys = query
                .select(Projections.constructor(SelfIntroMinForm.class, selfIntro.id, selfIntro.introName, selfIntro.lastUpdated))
                .from(selfIntro)
                .where(selfIntro.user.userId.eq(userId))
                .orderBy(selfIntro.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<SelfIntroMinForm> selfIntros = querys.fetch();
        long total = querys.fetchCount();

        return new PageImpl<>(selfIntros, pageable, total);
    }

    //==자소서 이름으로 검색==//
    public List<SelfIntroduction> findByName(String userId, String introName) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QUser user = QUser.user;
        QSelfIntroduction selfIntro = QSelfIntroduction.selfIntroduction;

        BooleanBuilder builder = new BooleanBuilder(); // BooleanBuilder를 사용하여 조건을 구성
        builder.and(user.userId.eq(userId)); // userID 조건 추가
        if (introName != null) { // introName이 null이 아닐 때만 조건 추가
            builder.and(selfIntro.introName.eq(introName));
        }

        return query
                .selectFrom(selfIntro)
                .join(user)
                .on(selfIntro.user.eq(user))
                .where(builder) // 수정된 조건 사용
                .fetch();
    }

}
