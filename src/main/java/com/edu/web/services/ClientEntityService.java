package com.edu.web.services;

import com.edu.web.entities.Client;

import java.util.List;

public interface ClientEntityService {
    void create(Client client);
    List<Client> getAll();
    Client getById(int id);
    boolean update(int id, Client client);
    boolean delete(int id);
}
