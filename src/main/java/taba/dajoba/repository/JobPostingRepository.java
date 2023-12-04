package taba.dajoba.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taba.dajoba.domain.*;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobPostingRepository {

    private final EntityManager em;

    //==채용 공고 하나 삭제==//
    public void delete(JobPosting jobPosting) {
        em.remove(jobPosting);
    }

    //==채용 공고 하나 조회==//
    public JobPosting findOne(Long id) {
        return em.find(JobPosting.class, id);
    }


    //모든 채용 공고 조회==//
    public List<JobPosting> showAllFrequent() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QJobPosting jobPosting = QJobPosting.jobPosting;
        return query
                .select(jobPosting)
                .from(jobPosting)
                .where(jobPosting.group.eq(JobPostingGroup.FREQUENT))
                .fetch();
    }

    //모든 공채 공고 조회==//
    public List<JobPosting> showAllPeriodic() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QJobPosting jobPosting = QJobPosting.jobPosting;
        return query
                .select(jobPosting)
                .from(jobPosting)
                .where(jobPosting.group.eq(JobPostingGroup.PERIODIC))
                .fetch();
    }

}
