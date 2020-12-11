package com.edu.web.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rate_service")
public class Traffic implements Serializable {

    private static final long serialVersionUID = 6L;

    @EmbeddedId
    @Getter
    @Setter
    private TrafficId trafficId;

    @ManyToOne
    @MapsId("rateId")
    @JsonManagedReference
    @Getter
    @Setter
    private Rate rate;

    @ManyToOne
    @MapsId("serviceId")
    @JsonManagedReference(value = "service-rates")
    @Getter
    @Setter
    private Service service;

    @Column(name = "traffic")
    @Getter
    @Setter
    private Integer traffic;

    public Traffic() {
    }

    public Traffic(Integer traffic) {
        this.traffic = traffic;
    }

    public Traffic(Rate rate, Service service, Integer traffic) {
        this.rate = rate;
        this.service = service;
        this.traffic = traffic;
    }
}
