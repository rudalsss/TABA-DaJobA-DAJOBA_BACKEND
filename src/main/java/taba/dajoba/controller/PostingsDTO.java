package taba.dajoba.controller;

import taba.dajoba.domain.JobPosting;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

public class PostingsDTO {

    private String groupIntro;
    private String mainduties;
    private String qualification;
    private String preferential;

}
