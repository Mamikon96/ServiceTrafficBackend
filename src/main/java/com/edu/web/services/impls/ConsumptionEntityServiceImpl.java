package com.edu.web.services.impls;

import com.edu.web.entities.Consumption;
import com.edu.web.services.ConsumptionEntityService;
import com.edu.web.entities.ConsumptionId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ConsumptionEntityServiceImpl implements ConsumptionEntityService {

    private static final Map<ConsumptionId, Consumption> CONSUMPTION_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();
    private static final AtomicInteger SERVICE_ID_HOLDER = new AtomicInteger();


    @Override
    public void create(Consumption consumption) {
        final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
        final int serviceId = SERVICE_ID_HOLDER.incrementAndGet();

        final ConsumptionId consumptionId = new ConsumptionId(clientId, serviceId);
        consumption.setId(consumptionId);
        CONSUMPTION_REPOSITORY_MAP.put(consumptionId, consumption);
    }

    @Override
    public List<Consumption> getAll() {
        return new ArrayList<>(CONSUMPTION_REPOSITORY_MAP.values());
    }

    @Override
    public List<Consumption> getByClientId(int clientId) {
        return new ArrayList<>(CONSUMPTION_REPOSITORY_MAP.values()).stream()
                .filter(item -> item.getId().getClientId() == clientId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Consumption> getByServiceId(int serviceId) {
        return new ArrayList<>(CONSUMPTION_REPOSITORY_MAP.values()).stream()
                .filter(item -> item.getId().getServiceId() == serviceId)
                .collect(Collectors.toList());
    }

    @Override
    public Consumption getByConsumptionId(ConsumptionId consumptionId) {
        return CONSUMPTION_REPOSITORY_MAP.get(consumptionId);
    }

    @Override
    public boolean update(ConsumptionId id, Consumption consumption) {
        if (CONSUMPTION_REPOSITORY_MAP.containsKey(id)) {
            consumption.setId(id);
            CONSUMPTION_REPOSITORY_MAP.put(id, consumption);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(ConsumptionId id) {
        return CONSUMPTION_REPOSITORY_MAP.remove(id) != null;
    }
}
