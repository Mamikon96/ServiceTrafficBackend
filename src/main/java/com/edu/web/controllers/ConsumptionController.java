package com.edu.web.controllers;

import com.edu.web.entities.*;
import com.edu.web.exceptions.DBConnectionException;
import com.edu.web.services.ConsumptionEntityService;
import com.edu.web.services.TrafficEntityService;
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
public class ConsumptionController {

    private final ConsumptionEntityService consumptionEntityService;

    @Autowired
    public ConsumptionController(ConsumptionEntityService consumptionEntityService) {
        this.consumptionEntityService = consumptionEntityService;
    }


    @PostMapping("/consumptions")
    public ResponseEntity<?> create(@RequestBody Consumption consumption) {
        try {
            Consumption newConsumption = new Consumption(consumption.getConsumptionTraffic());
            newConsumption.setConsumptionId(
                    new ConsumptionId(
                            consumption.getConsumptionId().getClientId(),
                            consumption.getConsumptionId().getServiceId()
                    ));
            Client client = new Client();
            client.setClientId(consumption.getConsumptionId().getClientId());
            Service service = new Service();
            service.setServiceId(consumption.getConsumptionId().getServiceId());
            newConsumption.setClient(client);
            newConsumption.setService(service);

            consumptionEntityService.create(newConsumption);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DBConnectionException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/consumptions")
    public ResponseEntity<List<Consumption>> getAll() {
        try {
            final List<Consumption> consumptions = consumptionEntityService.getAll();
            return consumptions != null
                    ? new ResponseEntity<>(consumptions, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/consumptions/client/{id}")
    public ResponseEntity<List<Consumption>> getByClientId(@PathVariable(name = "id") int id) {
        try {
            final List<Consumption> consumptions = consumptionEntityService.getByClientId(id);
            return consumptions != null
                    ? new ResponseEntity<>(consumptions, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/consumptions/service/{id}")
    public ResponseEntity<List<Consumption>> getByServiceId(@PathVariable(name = "id") int id) {
        try {
            final List<Consumption> consumptions = consumptionEntityService.getByServiceId(id);
            return consumptions != null
                    ? new ResponseEntity<>(consumptions, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/consumptions/client/{clientId}/service/{serviceId}")
    public ResponseEntity<Consumption> getByConsumptionId(@PathVariable(name = "clientId") Integer clientId,
                                                  @PathVariable(name = "serviceId") Integer serviceId) {
        try {
            ConsumptionId consumptionId = new ConsumptionId(clientId, serviceId);
            final Consumption consumption = consumptionEntityService.getByConsumptionId(consumptionId);
            return consumption != null
                    ? new ResponseEntity<>(consumption, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/consumptions")
    public ResponseEntity<?> update(@RequestBody Consumption consumption) {
        try {
            final boolean updated = consumptionEntityService.update(consumption);
            return updated
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/consumptions/client/{clientId}/service/{serviceId}")
    public ResponseEntity<?> delete(@PathVariable(name = "clientId") int clientId,
                                    @PathVariable(name = "serviceId") int serviceId) {
        try {
            ConsumptionId consumptionId = new ConsumptionId(clientId, serviceId);
            final boolean deleted = consumptionEntityService.delete(consumptionId);
            return deleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
