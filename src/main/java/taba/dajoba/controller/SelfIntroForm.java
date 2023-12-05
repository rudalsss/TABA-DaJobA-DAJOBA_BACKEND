package taba.dajoba.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import taba.dajoba.domain.Field;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SelfIntroForm {

    private String introName;
    private String introContent;
    private Field field;
    private LocalDate lastUpdated;

}