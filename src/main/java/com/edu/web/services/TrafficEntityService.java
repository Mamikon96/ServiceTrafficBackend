package com.edu.web.services;

import com.edu.web.entities.Traffic;
import com.edu.web.entities.TrafficId;
import com.edu.web.exceptions.DBConnectionException;

import java.util.List;

public interface TrafficEntityService {
    void create(Traffic traffic) throws DBConnectionException;
    List<Traffic> getAll() throws DBConnectionException;
    List<Traffic> getByRateId(int rateId) throws DBConnectionException;
    List<Traffic> getByServiceId(int serviceId) throws DBConnectionException;
    Traffic getByTrafficId(TrafficId trafficId) throws DBConnectionException;
    boolean update(Traffic traffic) throws DBConnectionException;
    boolean delete(TrafficId trafficId) throws DBConnectionException;
}
