package taba.dajoba.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DesireRegion {

    @Column
    private String desireProvince;

    @Column
    private String desireCity;

    public DesireRegion() {
    }

    public DesireRegion(String desireProvince, String desireCity) {
        this.desireProvince = desireProvince;
        this.desireCity = desireCity;
    }
}
