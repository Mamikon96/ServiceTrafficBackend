package com.edu.web.controllers;

import com.edu.web.entities.Rate;
import com.edu.web.exceptions.DBConnectionException;
import com.edu.web.services.RateEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin
public class RateController {

    private final RateEntityService rateEntityService;

    @Autowired
    public RateController(RateEntityService rateEntityService) {
        this.rateEntityService = rateEntityService;
    }


    @PostMapping("/rates")
    public ResponseEntity<?> create(@RequestBody Rate rate) {
        Rate newRate = new Rate(rate.getRateName(),
                rate.getPrice(),
                rate.getExpirationDate(),
                rate.getServices());
        try {
            rateEntityService.create(newRate);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DBConnectionException ex) {
            log.error(ex.getCause().toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rates")
    public ResponseEntity<List<Rate>> getAll() {
        try {
            final List<Rate> rates = rateEntityService.getAll();
            return rates != null && !rates.isEmpty()
                    ? new ResponseEntity<>(rates, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getCause().toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rates/{id}")
    public ResponseEntity<Rate> getById(@PathVariable(name = "id") int id) {
        try {
            final Rate rate = rateEntityService.getById(id);
            return rate != null
                    ? new ResponseEntity<>(rate, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getCause().toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/rates")
    public ResponseEntity<?> update(@RequestBody Rate rate) {
        try {
            final boolean updated = rateEntityService.update(rate);
            return updated
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (DBConnectionException ex) {
            log.error(ex.getCause().toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/rates/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        try {
            final boolean deleted = rateEntityService.delete(id);
            return deleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (DBConnectionException ex) {
            log.error(ex.getCause().toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
