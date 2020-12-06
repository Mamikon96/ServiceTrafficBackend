package com.edu.web.services;

import com.edu.web.entities.Rate;

import java.util.List;

public interface RateEntityService {
    void create(Rate rate);
    List<Rate> getAll();
    Rate getById(int id);
    boolean update(int id, Rate rate);
    boolean delete(int id);
}
