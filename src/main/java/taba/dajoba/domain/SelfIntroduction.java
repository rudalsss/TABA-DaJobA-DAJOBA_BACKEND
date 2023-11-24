package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="self_introduction")
@Getter
public class SelfIntroduction {
    @Id @GeneratedValue
    @Column(name = "intro_id")
    private Long id;

    @Column(name = "intro_name")
    private String name;

    @Column(name = "intro_content")
    private String introContent;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "desire_field")
    @Enumerated(EnumType.STRING)
    private DesireField field;

    @OneToMany(mappedBy = "selfIntroduction")
    private List<Match> matches = new ArrayList<>();
}
