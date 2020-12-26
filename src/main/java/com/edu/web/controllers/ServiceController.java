package com.edu.web.controllers;

import com.edu.web.entities.Service;
import com.edu.web.exceptions.DBConnectionException;
import com.edu.web.services.ServiceEntityService;
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
public class ServiceController {

    private final ServiceEntityService serviceEntityService;

    @Autowired
    public ServiceController(ServiceEntityService serviceEntityService) {
        this.serviceEntityService = serviceEntityService;
    }


    @PostMapping("/services")
    public ResponseEntity<?> create(@RequestBody Service service) {
        try {
            Service newService = new Service(service.getServiceName());
            serviceEntityService.create(newService);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DBConnectionException ex) {
            log.error(ex.getCause().toString());
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/services")
    public ResponseEntity<List<Service>> getAll() {
        try {
            final List<Service> services = serviceEntityService.getAll();
            return services != null && !services.isEmpty()
                    ? new ResponseEntity<>(services, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getCause().toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<Service> getById(@PathVariable(name = "id") int id) {
        try {
            final Service service = serviceEntityService.getById(id);
            return service != null
                    ? new ResponseEntity<>(service, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBConnectionException ex) {
            log.error(ex.getCause().toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/services")
    public ResponseEntity<?> update(@RequestBody Service service) {
        try {
            final boolean updated = serviceEntityService.update(service);
            return updated
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (DBConnectionException ex) {
            log.error(ex.getCause().toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        try {
            final boolean deleted = serviceEntityService.delete(id);
            return deleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (DBConnectionException ex) {
            log.error(ex.getCause().toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
