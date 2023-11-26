package taba.dajoba.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DesireRegion {

    @Column(name = "desire_province")
    private String desireProvince;

    @Column(name = "desire_city")
    private String city;

}
