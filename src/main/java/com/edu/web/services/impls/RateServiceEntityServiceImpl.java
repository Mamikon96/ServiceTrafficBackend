package com.edu.web.services.impls;

import com.edu.web.entities.RateService;
import com.edu.web.entities.RateServiceId;
import com.edu.web.services.RateServiceEntityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class RateServiceEntityServiceImpl implements RateServiceEntityService {

    private static final Map<RateServiceId, RateService> RATE_SERVICE_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger RATE_ID_HOLDER = new AtomicInteger();
    private static final AtomicInteger SERVICE_ID_HOLDER = new AtomicInteger();


    @Override
    public void create(RateService rateService) {
        final int rateId = RATE_ID_HOLDER.incrementAndGet();
        final int serviceId = SERVICE_ID_HOLDER.incrementAndGet();

        final RateServiceId rateServiceId = new RateServiceId(rateId, serviceId);
        rateService.setId(rateServiceId);
        RATE_SERVICE_REPOSITORY_MAP.put(rateServiceId, rateService);
    }

    @Override
    public List<RateService> getAll() {
        return new ArrayList<>(RATE_SERVICE_REPOSITORY_MAP.values());
    }

    @Override
    public List<RateService> getByRateId(int rateId) {
        return new ArrayList<>(RATE_SERVICE_REPOSITORY_MAP.values()).stream()
                .filter(item -> item.getId().getRateId() == rateId)
                .collect(Collectors.toList());
    }

    @Override
    public List<RateService> getByServiceId(int serviceId) {
        return new ArrayList<>(RATE_SERVICE_REPOSITORY_MAP.values()).stream()
                .filter(item -> item.getId().getServiceId() == serviceId)
                .collect(Collectors.toList());
    }

    @Override
    public RateService getByRateServiceId(RateServiceId rateServiceId) {
        return RATE_SERVICE_REPOSITORY_MAP.get(rateServiceId);
    }

    @Override
    public boolean update(RateServiceId id, RateService rateService) {
        if (RATE_SERVICE_REPOSITORY_MAP.containsKey(id)) {
            rateService.setId(id);
            RATE_SERVICE_REPOSITORY_MAP.put(id, rateService);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(RateServiceId id) {
        return RATE_SERVICE_REPOSITORY_MAP.remove(id) != null;
    }
}
