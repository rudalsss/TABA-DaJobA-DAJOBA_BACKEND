package taba.dajoba.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter @Getter
@NoArgsConstructor
public class JobPostingForm {
    private String title;

    private Date recruitDeadline;

    private String workingArea;

    private int jobGroup;

    private String groupIntro;

    private String mainduties;

    private String qualification;

    private String preferential;

    private String benefits;

    private String titleImg;

    private String logoImg;

    private String company;

}
