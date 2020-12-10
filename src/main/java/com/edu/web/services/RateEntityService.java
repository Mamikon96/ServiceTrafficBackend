package com.edu.web.services;

import com.edu.web.entities.Rate;
import com.edu.web.exceptions.DBConnectionException;

import java.util.List;

public interface RateEntityService {
    void create(Rate rate) throws DBConnectionException;
    List<Rate> getAll() throws DBConnectionException;
    Rate getById(int id) throws DBConnectionException;
    boolean update(int id, Rate rate) throws DBConnectionException;
    boolean delete(int id) throws DBConnectionException;
}
