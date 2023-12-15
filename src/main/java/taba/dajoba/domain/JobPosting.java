package taba.dajoba.domain;

import lombok.Getter;
import taba.dajoba.controller.JobPostingForm;
import taba.dajoba.controller.MatchForm;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name ="job_posting")
@Getter
@SequenceGenerator(
        name="job_posting_seq", sequenceName = "job_posting_seq", initialValue = 1, allocationSize = 1
)
public class JobPosting {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "job_posting_seq")
    @Column(name = "job_posting_id")
    private Long id;

    private String title;

    private Date recruitDeadline;

    private String workingArea;

    private String jobPostingUrl;

    private int jobGroup;

    @Lob
    private String groupIntro;

    @Lob
    private String mainduties;

    @Lob
    private String qualification;

    @Lob
    private String preferential;

    @Lob
    private String benefits;

    private String titleImg;

    private String logoImg;

    private String company;

    @OneToMany(mappedBy = "jobPosting")
    private List<Match> matches;

    //Entity-> Form 변환메서드
    public static JobPostingForm toJobPostingForm(JobPosting jobPosting){
        JobPostingForm jobPostingForm = new JobPostingForm();
        jobPostingForm.setCompany(jobPosting.getCompany());
        jobPostingForm.setTitle(jobPosting.getTitle());
        jobPostingForm.setRecruitDeadline(jobPosting.getRecruitDeadline());
        jobPostingForm.setWorkingArea(jobPosting.getWorkingArea());
        jobPostingForm.setQualification(jobPosting.getQualification());
        jobPostingForm.setJobGroup(jobPosting.getJobGroup());
        jobPostingForm.setGroupIntro(jobPosting.getGroupIntro());
        jobPostingForm.setPreferential(jobPosting.getPreferential());
        jobPostingForm.setMainduties(jobPosting.getMainduties());
        jobPostingForm.setBenefits(jobPosting.getBenefits());
        jobPostingForm.setTitleImg(jobPosting.getTitleImg());
        jobPostingForm.setLogoImg(jobPosting.getLogoImg());
        return jobPostingForm;
    }
}
