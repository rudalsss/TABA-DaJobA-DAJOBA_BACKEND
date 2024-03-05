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

    //전체 채용 공고 전달
    @GetMapping("jobs")
    public Page<JobPostingMinForm> showAllJobPostings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jobPostingService.showAllJobPostings(pageable);
    }

    //직군별 채용 공고 전달
    @GetMapping("jobs/{jobGroup}")
    public Page<JobPostingMinForm> showSpecificJobPostings(//@ModelAttribute("jobPostingForm") JobPostingForm form,
            @PathVariable int jobGroup,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jobPostingService.showSpecificJobPostings(jobGroup, pageable);
    }

    // 홈페이지에 정보 전달
    @GetMapping("jobs/latest")
    public List<JobPostingMinForm> topFour() {
        return jobPostingService.topFourFrequent();
    }

    //채용 상세정보 전달
    @GetMapping("jobs/detail/{jobid}")
    public JobPostingForm showJobPosting(@PathVariable long jobid){
        return jobPostingService.findOne(jobid);
    }
}
