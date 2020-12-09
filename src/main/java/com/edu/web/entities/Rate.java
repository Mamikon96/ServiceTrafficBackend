package com.edu.web.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rate")
public class Rate implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer rateId;

    @Column(name = "rate_name")
    @Getter
    @Setter
    private String rateName;

    @Column(name = "rate_price")
    @Getter
    @Setter
    private Double price;

    @Column(name = "expiration_date")
    @Getter
    @Setter
    private Date expirationDate;

    @OneToMany(
            mappedBy = "rate",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    @Getter
    @Setter
    private List<Traffic> services = new ArrayList<>();

    @OneToOne(mappedBy = "rate")
    private Client client;

    public Rate() {
    }

    public Rate(String rateName, Double price, List<Traffic> services) {
        this.rateName = rateName;
        this.price = price;
        this.services = services;
    }

    public Rate(String rateName, Double price, Date expirationDate, List<Traffic> services) {
        this.rateName = rateName;
        this.price = price;
        this.expirationDate = expirationDate;
        this.services = services;
    }

    /*@Override
    public String toString() {
        return "Rate{" +
                "rateId=" + rateId +
                ", rateName='" + rateName + '\'' +
                ", price=" + price +
                ", expirationDate=" + expirationDate +
                '}';
    }*/
}
