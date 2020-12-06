package com.edu.web.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "consumption")
public class Consumption implements Serializable {

    private static final long serialVersionUID = 4L;

    @EmbeddedId
    @Getter
    @Setter
    private ConsumptionId id = new ConsumptionId();

    @ManyToOne
    @MapsId("clientId")
    @Getter
    @Setter
    private Client client;

    @ManyToOne
    @MapsId("serviceId")
    @Getter
    @Setter
    private Service service;

    /*@OneToOne(cascade = CascadeType.ALL)
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    private Service service;*/

    @Column(name = "consumption_traffic")
    @Getter
    @Setter
    private Double consumptionTraffic;

    public Consumption() {
    }

    /*public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }*/

    /*public double getConsumptionTraffic() {
        return consumptionTraffic;
    }

    public void setConsumptionTraffic(double consumptionTraffic) {
        this.consumptionTraffic = consumptionTraffic;
    }
*/
    @Override
    public String toString() {
        return "Consumption{" +
                "id=" + id +
                ", client=" + client +
                ", service=" + service +
                ", consumptionTraffic=" + consumptionTraffic +
                '}';
    }

}
