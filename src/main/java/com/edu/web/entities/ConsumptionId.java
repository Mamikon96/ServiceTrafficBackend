package com.edu.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Embeddable
public class ConsumptionId implements Serializable {
    private static final long serialVersionUID = 5L;

    @Getter
    @Setter
    private Integer clientId;

    @Getter
    @Setter
    private Integer serviceId;

    public ConsumptionId() {
    }

    public ConsumptionId(Integer clientId, Integer serviceId) {
        this.clientId = clientId;
        this.serviceId = serviceId;
    }
}
