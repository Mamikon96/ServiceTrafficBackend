package com.edu.web.services;

import com.edu.web.entities.Service;
import com.edu.web.exceptions.DBConnectionException;

import java.util.List;

public interface ServiceEntityService {
    void create(Service service) throws DBConnectionException;
    List<Service> getAll() throws DBConnectionException;
    Service getById(Integer id) throws DBConnectionException;
    boolean update(int id, Service service) throws DBConnectionException;
    boolean delete(int id) throws DBConnectionException;
}
