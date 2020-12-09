package com.edu.web.services;

import com.edu.web.entities.Traffic;
import com.edu.web.entities.TrafficId;
import com.edu.web.exceptions.DBConnectionException;

import java.util.List;

public interface TrafficEntityService {
    void create(Traffic traffic) throws DBConnectionException;
    List<Traffic> getAll() throws DBConnectionException;
    List<Traffic> getByRateId(int rateId);
    List<Traffic> getByServiceId(int serviceId);
    Traffic getByTrafficId(TrafficId trafficId);
    boolean update(Traffic traffic);
    boolean delete(Traffic traffic);
}
