package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="self_introduction")
@Getter
public class SelfIntroduction {
    @Id
    @Column(name = "intro_id")
    private Long id;

    private String introName;

    @Lob
    private String introContent;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private DesireField desireField;

    @OneToMany(mappedBy = "selfIntroduction", fetch = FetchType.LAZY)
    private List<Match> matches = new ArrayList<>();
}
