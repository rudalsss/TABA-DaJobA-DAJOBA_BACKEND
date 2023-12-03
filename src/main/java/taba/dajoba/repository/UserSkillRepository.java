package taba.dajoba.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taba.dajoba.domain.QUserSkill;
import taba.dajoba.domain.User;
import taba.dajoba.domain.UserSkill;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserSkillRepository {

    private final EntityManager em;

    //자격정보 조회
//    public List<Certificate> findUserCertificate( String userId ){
//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//        QUser user = QUser.user;
//        QUserSkill userSkill = QUserSkill.userSkill;
//
//        List<Certificate> results = queryFactory
//                .select(userSkill.certificate)
//                .from(userSkill)
//                .join(user)
//                .on( userSkill.user.id.eq(user.id) )
//                .where( user.userId.eq(userId) )
//                .fetch();
//
//        return results;
//    }

    //UserSkill 조회
    public List<UserSkill> findUserSkill(User user){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QUserSkill userSkill = QUserSkill.userSkill;
        List<UserSkill> results = queryFactory
                .select(userSkill)
                .from(userSkill)
                .where(userSkill.user.eq(user))
                .fetch();
        return results;
    }

    //UserSkill 제거
    public void removeUserSkill(UserSkill userSkill){
        em.remove(userSkill);
    }

    //UserSkill 등록
    public void save(UserSkill userSkill){
        em.persist(userSkill);
    }
}
