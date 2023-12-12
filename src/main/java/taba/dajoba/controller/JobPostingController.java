package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import taba.dajoba.domain.JobPosting;
import taba.dajoba.service.JobPostingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    // 채용 공고 전달
//    @GetMapping("jobs")
//    public List<JobPosting> showFrequent(){
//        return jobPostingService.showAllFrequent();
//    }
    //채용 공고 전달 - 페이징 적용
    @GetMapping("jobs")
    public Page<JobPosting> showAllJobPostings(
            @RequestParam(defaultValue = "0") int page) {
        int size = 40; // 페이지당 보여질 항목 수
        Pageable pageable = PageRequest.of(page, size);
        return jobPostingService.showAllJobPostings(pageable);
    }

    // 홈페이지에 정보 전달
    @GetMapping("jobs/latest")
    public List<JobPosting> topFour() {
        return jobPostingService.topFourFrequent();
    }

    //채용 상세정보 전달
    @GetMapping("jobs/{jobid}")
    public JobPosting showJobPosting(@PathVariable long jobid){
        return jobPostingService.findOne(jobid);
    }
}
