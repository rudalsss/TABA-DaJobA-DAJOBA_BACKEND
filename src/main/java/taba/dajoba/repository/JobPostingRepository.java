package taba.dajoba.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taba.dajoba.domain.*;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobPostingRepository {

    private final EntityManager em;

    //==채용 공고 하나 삭제==//
    public void delete(JobPosting jobPosting) {
        em.remove(jobPosting);
    }

    //==deadLine 지난 공고 삭제==//
    public Long deleteOverdated(){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QJobPosting jobPosting = QJobPosting.jobPosting;
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);
        return query
                .delete(jobPosting)
                .where(jobPosting.recruitDeadline.before(date))
                .execute();
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

    //채용 공고 4개 뽑아내기
    public List<JobPosting> topFourFrequent() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QJobPosting jobPosting = QJobPosting.jobPosting;
        return query
                .select(jobPosting)
                .from(jobPosting)
                .where(jobPosting.group.eq(JobPostingGroup.FREQUENT))
                .limit(4)
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

    //(필터링을 위한) 채용&공채 조회
//    public List<Tuple> findAll(){
//        JPAQueryFactory query = new JPAQueryFactory(em);
//        QJobPosting jobPosting = QJobPosting.jobPosting;
//        QJobPostingDetail jobPostingDetail = QJobPostingDetail.jobPostingDetail;
//
//        List<Tuple> fetch = query
//                .select(jobPosting.workingArea, jobPostingDetail.mainduties)
//                .from(jobPosting)
//                .join(jobPostingDetail)
//                .on(jobPosting.id.eq(jobPostingDetail.id))
//                .fetch();
//
//        return fetch;
//    }

    //필터링 쿼리
    public List<Tuple> filterPosting( String province, String city, String mainduties ){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QJobPosting jobPosting = QJobPosting.jobPosting;
        QJobPostingDetail jobPostingDetail = QJobPostingDetail.jobPostingDetail;

        List<Tuple> fetch = query
                .select(jobPosting.title, jobPostingDetail)
                .from(jobPosting)
                .join(jobPostingDetail)
                .on(jobPosting.id.eq(jobPostingDetail.id))
                .where(jobPosting.workingArea.like("%" + province + "%")
                        .and(jobPosting.workingArea.like("%" + city + "%"))
                        .and(jobPostingDetail.mainduties.like("%" + mainduties + "%")))
                .fetch();

        return fetch;
    }

}
