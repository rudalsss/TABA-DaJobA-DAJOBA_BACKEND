package taba.dajoba.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import taba.dajoba.domain.AcademicBackgroundGroup;
import taba.dajoba.domain.DesireRegion;

@Getter @Setter
@AllArgsConstructor
public class UserExtraInfo {
    private AcademicBackgroundGroup academicBackground;
    private int experience;
    private String desireProvince;
    private String desireCity;
    public UserExtraInfo(){ }
}
