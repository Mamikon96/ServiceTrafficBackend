package com.edu.web.services;

import com.edu.web.entities.Client;
import com.edu.web.exceptions.DBConnectionException;

import java.util.List;

public interface ClientEntityService {
    void create(Client client) throws DBConnectionException;
    List<Client> getAll() throws DBConnectionException;
    Client getById(int id) throws DBConnectionException;
    List<Client> getByRateId(int rateId) throws DBConnectionException;
    boolean update(Client client) throws DBConnectionException;
    boolean delete(int id) throws DBConnectionException;
}
