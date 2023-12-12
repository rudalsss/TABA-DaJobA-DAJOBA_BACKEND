package taba.dajoba.controller;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class SelfIntroForm {

    private String introName;
    private String introContent;
    private int field;
    private LocalDate lastUpdated;

}