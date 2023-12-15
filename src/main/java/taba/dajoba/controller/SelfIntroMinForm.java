package taba.dajoba.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
public class SelfIntroMinForm {
    private String introId;
    private String introName;
    private LocalDate lastUpdated;

    public SelfIntroMinForm(Long introId, String introName, LocalDate lastUpdated) {
        this.introId = String.valueOf(introId);
        this.introName = introName;
        this.lastUpdated = lastUpdated;
    }
}
