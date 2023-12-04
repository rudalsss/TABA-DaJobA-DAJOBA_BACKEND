package taba.dajoba.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taba.dajoba.domain.*;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    //test용
    public User findOne(Long id){ //test용
        return em.find(User.class, id);
    }

    //userId로 검색하기
    public List<User> findByUserId(String userId){
        return em.createQuery("select u from User u where u.userId = :userId",
                        User.class)
                .setParameter("userId", userId)
                .getResultList();
    }

}