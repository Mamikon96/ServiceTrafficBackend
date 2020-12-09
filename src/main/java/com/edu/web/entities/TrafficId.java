package com.edu.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@ToString
public class TrafficId implements Serializable {
    private static final long serialVersionUID = 7L;

    @Column(name = "rate_id")
    @Getter
    @Setter
    private Integer rateId;

    @Column(name = "service_id")
    @Getter
    @Setter
    private Integer serviceId;

    public TrafficId() {
    }

    public TrafficId(Integer rateId, Integer serviceId) {
        this.rateId = rateId;
        this.serviceId = serviceId;
    }
}
