package com.edu.web.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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

    @ManyToMany(mappedBy = "rates")
    @Getter
    @Setter
    private Set<Service> services;

    public Rate() {
    }

    public Rate(String rateName, Double price, Set<Service> services) {
        this.rateName = rateName;
        this.price = price;
        this.services = services;
    }

    public Rate(String rateName, Double price, Date expirationDate, Set<Service> services) {
        this.rateName = rateName;
        this.price = price;
        this.expirationDate = expirationDate;
        this.services = services;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rateId=" + rateId +
                ", rateName='" + rateName + '\'' +
                ", price=" + price +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
