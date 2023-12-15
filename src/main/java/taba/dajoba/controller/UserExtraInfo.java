package taba.dajoba.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba.dajoba.domain.AcademicBackgroundGroup;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserExtraInfo {
    private AcademicBackgroundGroup academicBackground;
    private int experience;
    private String desireProvince;
    private String desireCity;
}
