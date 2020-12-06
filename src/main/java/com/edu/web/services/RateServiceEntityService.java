package com.edu.web.services;

import com.edu.web.entities.RateService;
import com.edu.web.entities.RateServiceId;

import java.util.List;

public interface RateServiceEntityService {
    void create(RateService rateService);
    List<RateService> getAll();
    List<RateService> getByRateId(int rateId);
    List<RateService> getByServiceId(int serviceId);
    RateService getByRateServiceId(RateServiceId rateServiceId);
    boolean update(RateServiceId id, RateService rateService);
    boolean delete(RateServiceId id);
}
