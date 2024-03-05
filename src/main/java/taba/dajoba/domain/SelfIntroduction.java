package taba.dajoba.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import taba.dajoba.controller.SelfIntroForm;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(columnDefinition = "DATE")
    private LocalDate lastUpdated;  // 마지막으로 수정된 시각

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int desireField;    //희망분야

    @OneToMany(mappedBy = "selfIntroduction", cascade = CascadeType.REMOVE)
    private List<Match> matches = new ArrayList<>();

    @Column(columnDefinition = "number default 1")
    private int signal;

    //Form-> Entity 변환메서드
    public static SelfIntroduction toSelfIntroductionEntity (User user, SelfIntroForm selfIntroForm){
        SelfIntroduction selfIntroduction = new SelfIntroduction();
        selfIntroduction.introName = selfIntroForm.getIntroName();
        selfIntroduction.introContent = selfIntroForm.getIntroContent();
        selfIntroduction.user = user;
        selfIntroduction.desireField = selfIntroForm.getDesireField();
        selfIntroduction.lastUpdated = LocalDate.now();  // 생성 시점의 날짜로 초기화
        selfIntroduction.signal = 1;
        return selfIntroduction;
    }

    //Entity-> Form 변환메서드
    public static SelfIntroForm toSelfIntroductionForm (SelfIntroduction selfIntroduction){
        SelfIntroForm selfIntroForm = new SelfIntroForm();
        selfIntroForm.setIntroName(selfIntroduction.getIntroName());
        selfIntroForm.setIntroContent(selfIntroduction.getIntroContent());
        selfIntroForm.setLastUpdated(selfIntroduction.getLastUpdated());
        selfIntroForm.setDesireField(selfIntroduction.getDesireField());
        return selfIntroForm;
    }

    //==수정 메서드==//
    public boolean update(SelfIntroForm selfIntroForm) {
        boolean isContentChanged = !this.introContent.equals(selfIntroForm.getIntroContent());
        boolean isFieldChanged = this.desireField != selfIntroForm.getDesireField();
        this.introName = selfIntroForm.getIntroName();
        this.introContent = selfIntroForm.getIntroContent();
        this.desireField = selfIntroForm.getDesireField();
        this.lastUpdated = LocalDate.now();  // 수정 시점의 날짜로 업데이트
        return isContentChanged || isFieldChanged;
    }
    public void signalUpdate() {
        this.signal = 1;
    }

    //==조회 메서드==//
    //repository에 작성

    //시그널값 변경
    public void updateSignal(){
        this.signal = 1;
    }

}
