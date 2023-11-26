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

    @Column(columnDefinition = "NVARCHAR(200)")
    private String employmentTypeContent;

    @OneToMany(mappedBy = "employmentType", fetch = FetchType.LAZY)
    private List<JobPosting> jobPostings = new ArrayList<>();
}
