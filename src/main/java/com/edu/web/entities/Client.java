package com.edu.web.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientid")
    @Getter
    @Setter
    private Integer clientId;

    @ManyToOne
    @JoinColumn(name = "rate_rateid")
    @Getter
    @Setter
    private Rate rate;

    @Column(name = "client_name", unique = true)
    @Getter
    @Setter
    private String clientName;

    @Column(name = "connection_date")
    @Getter
    @Setter
    private Date connectionDate;

    @Column(name = "payment_date")
    @Getter
    @Setter
    private Date paymentDate;

    @Column(name = "discount")
    @Getter
    @Setter
    private Integer discount;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY // EAGER
    )
    @JsonBackReference(value = "client-services")
    @Getter
    @Setter
    private Set<Consumption> services = new HashSet<>();

    public Client() {
    }

    public Client(Rate rate, String clientName, Date connectionDate, Date paymentDate, Integer discount) {
        this.rate = rate;
        this.clientName = clientName;
        this.connectionDate = connectionDate;
        this.paymentDate = paymentDate;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", rate=" + rate +
                ", clientName='" + clientName + '\'' +
                ", connectionDate=" + connectionDate +
                ", paymentDate=" + paymentDate +
                ", discount=" + discount +
                '}';
    }
}
