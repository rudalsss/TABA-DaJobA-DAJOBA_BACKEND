package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class AcademicBackground {
    @Id
    @Column(name = "academic_background_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private AcademicBackgroundGroup academicBackgroundGroup;  // PERIODIC FREQUENT

    @OneToMany(mappedBy = "academicBackground", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "academicBackground", fetch = FetchType.LAZY)
    private List<JobPosting> jobPostings = new ArrayList<>();
}
