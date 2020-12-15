package com.edu.web.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer serviceId;

    @Column(name = "service_name", unique = true)
    @Getter
    @Setter
    private String serviceName;

    @OneToMany(
            mappedBy = "service",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY // EAGER
    )
    @JsonBackReference(value = "service-rates")
    @Getter
    @Setter
    private Set<Traffic> rates = new HashSet<>();

    @OneToMany(
            mappedBy = "service",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY // EAGER
    )
    @JsonBackReference(value = "service-clients")
    @Getter
    @Setter
    private Set<Consumption> clients = new HashSet<>();

    public Service() {
    }

    public Service(String serviceName) {
        this.serviceName = serviceName;
    }

}
