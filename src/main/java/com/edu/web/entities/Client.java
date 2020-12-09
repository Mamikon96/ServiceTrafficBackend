package com.edu.web.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer clientId;

    @OneToOne
    @JoinColumn(name = "rate_rateid")
    @Getter
    @Setter
    private Rate rate;

    @Column(name = "client_name")
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
