package com.edu.web.services.impls;

import com.edu.web.entities.Client;
import com.edu.web.services.ClientEntityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientEntityServiceImpl implements ClientEntityService {

    private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Client client) {
        final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
        client.setClientId(clientId);
        CLIENT_REPOSITORY_MAP.put(clientId, client);
    }

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(CLIENT_REPOSITORY_MAP.values());

    }

    @Override
    public Client getById(int id) {
        return CLIENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(int id, Client client) {
        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
            client.setClientId(id);
            CLIENT_REPOSITORY_MAP.put(id, client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return CLIENT_REPOSITORY_MAP.remove(id) != null;
    }
}
