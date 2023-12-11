package taba.dajoba.controller;

import lombok.*;
import taba.dajoba.domain.Field;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class SelfIntroForm {

    private String introName;
    private String introContent;
    private Field field;
    private LocalDate lastUpdated;

}