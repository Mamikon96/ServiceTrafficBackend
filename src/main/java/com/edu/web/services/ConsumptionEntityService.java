package com.edu.web.services;


import com.edu.web.entities.Consumption;
import com.edu.web.entities.ConsumptionId;

import java.util.List;

public interface ConsumptionEntityService {
    void create(Consumption consumption);
    List<Consumption> getAll();
    List<Consumption> getByClientId(int clientId);
    List<Consumption> getByServiceId(int serviceId);
    Consumption getByConsumptionId(ConsumptionId consumptionId);
    boolean update(ConsumptionId id, Consumption consumption);
    boolean delete(ConsumptionId id);
}
