package taba.dajoba.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobPostingForm {   //빠른 로딩을 위해 3개 데이터만 처리하기

    private String title;

    private Long id;

    private String titleImg;

}
