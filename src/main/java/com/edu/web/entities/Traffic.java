package com.edu.web.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonManagedReference(value = "rate-services")
    @JsonIgnore
    @Getter
    @Setter
    private Rate rate;

    @ManyToOne
    @MapsId("serviceId")
    @JsonManagedReference(value = "service-rates")
    @JsonIgnore
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

}
