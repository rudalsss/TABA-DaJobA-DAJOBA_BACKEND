package taba.dajoba.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.controller.JobPostingForm;
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

        Pageable pageable = PageRequest.of(4, 20);

        //when
        Page<JobPostingForm> jobpostings = jobPostingService.showAllJobPostings(pageable);

        //then

    }

}