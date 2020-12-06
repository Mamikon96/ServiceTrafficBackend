package com.edu.web.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "service")
@ToString
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer serviceId;

    @Column(name = "service_name")
    @Getter
    @Setter
    private String serviceName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "rate_service",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "rate_id")
    )
    @Getter
    @Setter
    private Set<Rate> rates;

    public Service() {
    }

    public Service(String serviceName) {
        this.serviceName = serviceName;
    }

}
