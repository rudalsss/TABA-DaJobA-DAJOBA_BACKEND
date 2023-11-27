package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SequenceGenerator(
        name="academic_background_seq", sequenceName = "academic_background_seq", initialValue = 1, allocationSize = 1
)
public class AcademicBackground {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "academic_background_seq")
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
