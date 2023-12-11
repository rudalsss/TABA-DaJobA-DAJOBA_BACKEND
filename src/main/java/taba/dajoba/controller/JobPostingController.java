package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba.dajoba.domain.JobPosting;
import taba.dajoba.service.JobPostingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    // 채용 공고 전달
    @GetMapping("jobs")
    public List<JobPosting> showFrequent(){
        return jobPostingService.showAllFrequent();
    }

    // 홈페이지에 정보 전달
    @GetMapping("jobs/latest")
    public List<JobPosting> topFour() {
        return jobPostingService.topFourFrequent();
    }
}
