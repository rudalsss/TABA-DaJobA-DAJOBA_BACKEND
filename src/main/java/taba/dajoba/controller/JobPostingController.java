package taba.dajoba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import taba.dajoba.domain.JobPosting;
import taba.dajoba.service.JobPostingService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    //채용 공고 전달
    @GetMapping("jobs/frequent")
    public String showFrequent(Model model){
        List<JobPosting> jobPostings = jobPostingService.showAllFrequent();
        model.addAttribute("jobPostings", jobPostings);
        return "어떤 URL";
    }

    //공채 정보 전달
    @GetMapping("jobs/periodic")
    public String showPeriodic(Model model){
        List<JobPosting> jobPostings = jobPostingService.showAllPeriodic();
        model.addAttribute("jobPostings", jobPostings);
        return "어떤 URL";
    }

    //홈페이지에 정보 전달
    @GetMapping("jobs/latest")
    public String topFour(Model model) {
        List<JobPosting> jobPostings = jobPostingService.topFourFrequent();
        model.addAttribute("jobPostings", jobPostings);
        return "어떤 URL";
    }
}
