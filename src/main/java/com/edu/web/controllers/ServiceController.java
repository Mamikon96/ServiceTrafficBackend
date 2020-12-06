package com.edu.web.controllers;

import com.edu.web.entities.Service;
import com.edu.web.services.ServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {

    private final ServiceEntityService serviceEntityService;

    @Autowired
    public ServiceController(ServiceEntityService serviceEntityService) {
        this.serviceEntityService = serviceEntityService;
    }


    @PostMapping(value = "/services")
    public ResponseEntity<?> create(@RequestBody Service service) {
        Service newService = new Service(service.getServiceName());
        serviceEntityService.create(newService);
//        System.out.println(service);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/services")
    public ResponseEntity<List<Service>> getAll() {
        final List<Service> services = serviceEntityService.getAll();

        return services != null &&  !services.isEmpty()
                ? new ResponseEntity<>(services, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/services/{id}")
    public ResponseEntity<Service> getById(@PathVariable(name = "id") int id) {
        final Service service = serviceEntityService.getById(id);

        return service != null
                ? new ResponseEntity<>(service, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/services/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Service service) {
        final boolean updated = serviceEntityService.update(id, service);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/services/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = serviceEntityService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
