package taba.dajoba.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.domain.JobPosting;
import taba.dajoba.repository.JobPostingRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JobPostingServiceTest {

    @Autowired JobPostingService jobPostingService;
    @Autowired JobPostingRepository jobPostingRepository;

    @Test
    public void 채용공고조회() throws Exception {
        //given


        //when
        List<JobPosting> jobpostings = jobPostingService.showAllFrequent();

        //then
        JobPosting jobposting = jobpostings.get(1);
        System.out.println(jobposting.getJobPostingUrl());
        System.out.println(jobposting.getId());
        System.out.println(jobposting.getJobGroup());
        System.out.println(jobposting.getCompany());
        System.out.println(jobposting.getRecruitDeadline());
        System.out.println(jobposting.getMainduties());
        System.out.println(jobposting.getTitle());
        System.out.println(jobposting.getBenefits());

    }

}