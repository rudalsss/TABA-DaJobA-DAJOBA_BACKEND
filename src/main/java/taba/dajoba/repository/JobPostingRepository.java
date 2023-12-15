package taba.dajoba.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import taba.dajoba.controller.JobPostingMinForm;
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


    //==모든 채용 공고 조회==//
    public Page<JobPostingMinForm> showAllJobPostings(Pageable pageable) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QJobPosting jobPosting = QJobPosting.jobPosting;

        JPAQuery<JobPostingMinForm> querys = query
                .select(Projections.bean(JobPostingMinForm.class, jobPosting.title, jobPosting.id, jobPosting.titleImg))
                .from(jobPosting)
                .orderBy(jobPosting.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<JobPostingMinForm> jobPostings = querys.fetch();
        long total = querys.fetchCount();

        return new PageImpl<>(jobPostings, pageable, total);
    }

    //==최신순으로 직군별 채용 공고 조회==//
    public Page<JobPostingMinForm> showSpecificJobPostings(int field, Pageable pageable) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QJobPosting jobPosting = QJobPosting.jobPosting;

        JPAQuery<JobPostingMinForm> querys = query
                .select(Projections.bean(JobPostingMinForm.class, jobPosting.title, jobPosting.id, jobPosting.titleImg))
                .from(jobPosting)
                .where(jobPosting.jobGroup.eq(field))
                .orderBy(jobPosting.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<JobPostingMinForm> jobPostings = querys.fetch();
        long total = querys.fetchCount();

        return new PageImpl<>(jobPostings, pageable, total);
    }


    //==채용 공고 4개 뽑아내기==//
    public List<JobPostingMinForm> topFourFrequent() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QJobPosting jobPosting = QJobPosting.jobPosting;
        return query
                .select(Projections.bean(JobPostingMinForm.class, jobPosting.title, jobPosting.id, jobPosting.titleImg))
                .from(jobPosting)
                .limit(4)
                .orderBy(jobPosting.id.desc())
                .fetch();
    }

}
