package com.edu.web.controllers;

import com.edu.web.entities.Rate;
import com.edu.web.services.RateEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RateController {

    private final RateEntityService rateEntityService;

    @Autowired
    public RateController(RateEntityService rateEntityService) {
        this.rateEntityService = rateEntityService;
    }


    @PostMapping(value = "/rates")
    public ResponseEntity<?> create(@RequestBody Rate rate) {
        Rate newRate = new Rate(rate.getRateName(),
                rate.getPrice(),
                rate.getExpirationDate(),
                rate.getServices());
        rateEntityService.create(newRate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/rates")
    public ResponseEntity<List<Rate>> getAll() {
        final List<Rate> rates = rateEntityService.getAll();

        return rates != null &&  !rates.isEmpty()
                ? new ResponseEntity<>(rates, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/rates/{id}")
    public ResponseEntity<Rate> getById(@PathVariable(name = "id") int id) {
        final Rate rate = rateEntityService.getById(id);

        return rate != null
                ? new ResponseEntity<>(rate, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/rates/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Rate rate) {
        final boolean updated = rateEntityService.update(id, rate);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/rates/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = rateEntityService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
