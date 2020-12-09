package com.edu.web.controllers;

import com.edu.web.entities.Rate;
import com.edu.web.entities.Service;
import com.edu.web.entities.Traffic;
import com.edu.web.entities.TrafficId;
import com.edu.web.exceptions.DBConnectionException;
import com.edu.web.services.TrafficEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class TrafficController {

    private final TrafficEntityService trafficEntityService;

    @Autowired
    public TrafficController(TrafficEntityService trafficEntityService) {
        this.trafficEntityService = trafficEntityService;
    }


    @PostMapping(value = "/traffics")
    public ResponseEntity<?> create(@RequestBody Traffic traffic) {
        try {
            Traffic newTraffic = new Traffic(traffic.getTraffic());
            newTraffic.setTrafficId(new TrafficId(traffic.getTrafficId().getRateId(), traffic.getTrafficId().getServiceId()));
            Rate rate = new Rate();
            rate.setRateId(traffic.getRate().getRateId());
            Service service = new Service();
            service.setServiceId(traffic.getService().getServiceId());
            newTraffic.setRate(rate);
            newTraffic.setService(service);
            trafficEntityService.create(newTraffic);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DBConnectionException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/traffics")
    public ResponseEntity<List<Traffic>> getAll() {
        try {
            final List<Traffic> traffics = trafficEntityService.getAll();
            return traffics != null
                    ? new ResponseEntity<>(traffics, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/traffics/rate/{id}")
    public ResponseEntity<List<Traffic>> getByRateId(@PathVariable(name = "id") int id) {
        final List<Traffic> traffics = trafficEntityService.getByRateId(id);

        return traffics != null && !traffics.isEmpty()
                ? new ResponseEntity<>(traffics, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/traffics/service/{id}")
    public ResponseEntity<List<Traffic>> getByServiceId(@PathVariable(name = "id") int id) {
        final List<Traffic> traffics = trafficEntityService.getByServiceId(id);

        return traffics != null && !traffics.isEmpty()
                ? new ResponseEntity<>(traffics, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/traffics/rate/{rateId}/service/{serviceId}")
    public ResponseEntity<Traffic> getByTrafficId(@PathVariable(name = "rateId") Integer rateId,
                                                  @PathVariable(name = "serviceId") Integer serviceId) {
        TrafficId trafficId = new TrafficId(rateId, serviceId);
        final Traffic traffic = trafficEntityService.getByTrafficId(trafficId);

        return traffic != null
                ? new ResponseEntity<>(traffic, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/traffics")
    public ResponseEntity<?> update(@RequestBody Traffic traffic) {
//        TrafficId trafficId = new TrafficId(rateId, serviceId);
        final boolean updated = trafficEntityService.update(/*trafficId, */traffic);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/traffics")
    public ResponseEntity<?> delete(@RequestBody Traffic traffic) {
        final boolean deleted = trafficEntityService.delete(traffic);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
