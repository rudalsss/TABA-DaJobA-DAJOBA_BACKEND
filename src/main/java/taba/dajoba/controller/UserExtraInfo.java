package taba.dajoba.controller;

import lombok.Getter;
import lombok.Setter;
import taba.dajoba.domain.DesireRegion;

@Getter @Setter
public class UserExtraInfo {
    private int experience;
    private DesireRegion desireRegion;
    private String jobContent;
}
