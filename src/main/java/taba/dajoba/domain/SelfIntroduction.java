package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name ="self_introduction")
@Getter
public class SelfIntroduction {
    @Id @GeneratedValue
    @Column(name = "instroduction_id")
    private Long id;

    private String introduction;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "desire_field")
    private String field;
}
