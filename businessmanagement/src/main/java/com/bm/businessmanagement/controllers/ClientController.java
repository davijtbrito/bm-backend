package com.bm.businessmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bm.businessmanagement.controllers.requests.CreateClientRequest;
import com.bm.businessmanagement.controllers.requests.UpdateClientRequest;
import com.bm.businessmanagement.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
 
    @Autowired
    private ClientService clientService;

    @PostMapping("/create")    
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Object> create(@RequestBody CreateClientRequest body){
        return ResponseEntity.ok().body(this.clientService.create(body.getDto()));
    }

    @PostMapping("/update")    
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Object> update(@RequestBody UpdateClientRequest body){
        return ResponseEntity.ok().body(this.clientService.update(body.getDto()));
    }

    @PostMapping("/activateDeactivate")
    @Secured({"ROLE_ADMIN"})
    public void activateDeactivate(@RequestParam Long id, @RequestParam Boolean isActive){
        this.clientService.activateDeactivate(id, isActive);
    }
}
