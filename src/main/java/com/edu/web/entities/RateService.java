package com.edu.web.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rate_service")
public class RateService implements Serializable {

    private static final long serialVersionUID = 6L;

    @EmbeddedId
    @Getter
    @Setter
    private RateServiceId id = new RateServiceId();

    @ManyToOne
    @MapsId("rateId")
    @Getter
    @Setter
    private Rate rate;

    @ManyToOne
    @MapsId("serviceId")
    @Getter
    @Setter
    private Service service;

    @Column(name = "traffic")
    @Getter
    @Setter
    private Integer traffic;

    public RateService() {
    }

    public RateService(Integer traffic) {
        this.traffic = traffic;
    }
}
