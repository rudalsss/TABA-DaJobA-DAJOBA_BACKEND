package taba.dajoba.controller;

import lombok.*;
import taba.dajoba.domain.User;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class SelfIntroForm {
    private String introName;
    private String introContent;
    private int desireField;
    private LocalDate lastUpdated;

}