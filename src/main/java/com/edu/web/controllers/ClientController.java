package com.edu.web.controllers;

import com.edu.web.entities.*;
import com.edu.web.exceptions.DBConnectionException;
import com.edu.web.services.ClientEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ClientController {

    private final ClientEntityService clientEntityService;

    @Autowired
    public ClientController(ClientEntityService clientEntityService) {
        this.clientEntityService = clientEntityService;
    }


    @PostMapping("/clients")
    public ResponseEntity<?> create(@RequestBody Client client) {
        try {
//            Rate rate = new Rate();
//            rate.setRateId(client.getRate().getRateId());
//            client.setRate(client.getRate());
            clientEntityService.create(client);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DBConnectionException ex) {
            log.error(ex.getCause().toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAll() {
        try {
            final List<Client> clients = clientEntityService.getAll();
            return clients != null
                    ? new ResponseEntity<>(clients, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("client/{id}")
    public ResponseEntity<Client> getById(@PathVariable(name = "id") Integer id) {
        try {
            final Client client = clientEntityService.getById(id);
            return client != null
                    ? new ResponseEntity<>(client, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("clients/rate/{rateId}")
    public ResponseEntity<List<Client>> getByRateId(@PathVariable(name = "rateId") Integer rateId) {
        try {
            final List<Client> clients = clientEntityService.getByRateId(rateId);
            return clients != null
                    ? new ResponseEntity<>(clients, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/clients")
    public ResponseEntity<?> update(@RequestBody Client client) {
        try {
            final boolean updated = clientEntityService.update(client);

            return updated
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        try {
            final boolean deleted = clientEntityService.delete(id);
            return deleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
