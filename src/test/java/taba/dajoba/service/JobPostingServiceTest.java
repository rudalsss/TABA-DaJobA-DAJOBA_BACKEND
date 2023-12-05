package taba.dajoba.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import taba.dajoba.repository.JobPostingRepository;

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

        //then
    }

}