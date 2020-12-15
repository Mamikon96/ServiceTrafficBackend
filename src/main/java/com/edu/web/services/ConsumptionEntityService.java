package com.edu.web.services;


import com.edu.web.entities.Consumption;
import com.edu.web.entities.ConsumptionId;
import com.edu.web.exceptions.DBConnectionException;

import java.util.List;

public interface ConsumptionEntityService {
    void create(Consumption consumption) throws DBConnectionException;
    List<Consumption> getAll() throws DBConnectionException;
    List<Consumption> getByClientId(int clientId) throws DBConnectionException;
    List<Consumption> getByServiceId(int serviceId) throws DBConnectionException;
    Consumption getByConsumptionId(ConsumptionId consumptionId) throws DBConnectionException;
    boolean update(Consumption consumption) throws DBConnectionException;
    boolean delete(ConsumptionId consumptionId) throws DBConnectionException;
}
