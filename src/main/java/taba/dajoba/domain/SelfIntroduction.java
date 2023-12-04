package taba.dajoba.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="self_introduction")
@Getter
@SequenceGenerator(
        name="self_introduction_seq", sequenceName = "self_introduction_seq", initialValue = 1, allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelfIntroduction {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "self_introduction_seq")
    @Column(name = "intro_id")
    private Long id;

    private String introName;   // 자기소개서 이름

    @Lob
    private String introContent;    //자기소개서 내용


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private DesireField desireField;    //희망분야-DesireField 참고

    @OneToMany(mappedBy = "selfIntroduction")
    private List<Match> matches = new ArrayList<>();

    //==생성 메서드==//
    public static SelfIntroduction create(String introName, String introContent, User user, DesireField desireField) {
        SelfIntroduction selfIntroduction = new SelfIntroduction();
        selfIntroduction.introName = introName;
        selfIntroduction.introContent = introContent;
        selfIntroduction.user = user;
        selfIntroduction.desireField = desireField;
        return selfIntroduction;
    }

    //==수정 메서드==//
    public void update(String introName, String introContent, DesireField desireField) {
        this.introName = introName;
        this.introContent = introContent;
        this.desireField = desireField;
    }

    //==조회 메서드==//
    //repository에 작성


}
