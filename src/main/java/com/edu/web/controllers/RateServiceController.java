package com.edu.web.controllers;

import com.edu.web.entities.Rate;
import com.edu.web.entities.RateService;
import com.edu.web.services.RateEntityService;
import com.edu.web.services.RateServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RateServiceController {

    private final RateServiceEntityService rateServiceEntityService;

    @Autowired
    public RateServiceController(RateServiceEntityService rateServiceEntityService) {
        this.rateServiceEntityService = rateServiceEntityService;
    }


    @PostMapping(value = "/rateService")
    public ResponseEntity<?> create(@RequestBody RateService rateService) {
        RateService newRateService = new RateService(rateService.getTraffic());
        rateServiceEntityService.create(newRateService);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/rateService")
    public ResponseEntity<List<RateService>> getAll() {
        final List<RateService> rateServices = rateServiceEntityService.getAll();

        return rateServices != null &&  !rateServices.isEmpty()
                ? new ResponseEntity<>(rateServices, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping(value = "/rateService/{id}")
    public ResponseEntity<Rate> getById(@PathVariable(name = "id") int id) {
        final Rate rate = rateServiceEntityService.getById(id);

        return rate != null
                ? new ResponseEntity<>(rate, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    /*@PutMapping(value = "/rateService/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Rate rate) {
        final boolean updated = rateServiceEntityService.update(id, rate);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }*/

    /*@DeleteMapping(value = "/rateService/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = rateServiceEntityService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }*/
}
