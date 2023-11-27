package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="employment_type")
@Getter
@SequenceGenerator(
        name="employment_type_seq", sequenceName = "employment_type_seq", initialValue = 1, allocationSize = 1
)
public class EmploymentType {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "employment_type_seq")
    @Column(name = "employment_type_id")
    private Long id;

    @Column(columnDefinition = "NVARCHAR(200)")
    private String employmentTypeContent;

    @OneToMany(mappedBy = "employmentType", fetch = FetchType.LAZY)
    private List<JobPosting> jobPostings = new ArrayList<>();
}
