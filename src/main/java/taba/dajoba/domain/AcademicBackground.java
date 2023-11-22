package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "academic_background")
@Getter
public class AcademicBackground {
    @Id @GeneratedValue
    @Column(name = "academic_background_id")
    private Long id;

    @Column(name = "academic_background_content")
    private String content;

    @OneToMany(mappedBy = "academicBackground")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "academicBackground")
    private List<JobPosting> jobPostings = new ArrayList<>();
}
