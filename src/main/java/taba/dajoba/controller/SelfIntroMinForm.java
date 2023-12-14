package taba.dajoba.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class SelfIntroMinForm {
    private Long introId;
    private String introName;
    private LocalDate lastUpdated;
}
