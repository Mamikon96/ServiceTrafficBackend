package com.edu.web.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Column(name = "service_name")
    @Getter
    @Setter
    private String serviceName;

    @OneToMany(
            mappedBy = "service",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference(value = "service-rates")
    @Getter
    @Setter
    private List<Traffic> rates = new ArrayList<>();

    @OneToMany(
            mappedBy = "service",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "service-clients")
    @Getter
    @Setter
    private List<Consumption> clients = new ArrayList<>();

    public Service() {
    }

    public Service(String serviceName) {
        this.serviceName = serviceName;
    }

}
