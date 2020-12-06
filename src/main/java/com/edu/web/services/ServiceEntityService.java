package com.edu.web.services;

import com.edu.web.entities.Service;

import java.util.List;

public interface ServiceEntityService {
    void create(Service service);
    List<Service> getAll();
    Service getById(Integer id);
    boolean update(int id, Service service);
    boolean delete(int id);
}
