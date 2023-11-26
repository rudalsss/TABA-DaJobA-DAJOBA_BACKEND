package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="employment_type")
@Getter
public class EmploymentType {
    @Id
    @Column(name = "employment_type_id")
    private Long id;

    @Column(name = "employment_type_content")
    private String content;

    @OneToMany(mappedBy = "employmentType")
    private List<JobPosting> jobPostings = new ArrayList<>();
}
