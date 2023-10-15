package com.bm.businessmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bm.businessmanagement.controllers.requests.CreateSupplierRequest;
import com.bm.businessmanagement.controllers.requests.UpdateSupplierRequest;
import com.bm.businessmanagement.services.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/create")    
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Object> create(@RequestBody CreateSupplierRequest body){
        return ResponseEntity.ok().body(this.supplierService.create(body.getDto()));
    }

    @PostMapping("/update")    
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Object> update(@RequestBody UpdateSupplierRequest body){
        return ResponseEntity.ok().body(this.supplierService.update(body.getDto()));
    }

    @PostMapping("/activateDeactivate")
    @Secured({"ROLE_ADMIN"})
    public void activateDeactivate(@RequestParam Long id, @RequestParam Boolean isActive){
        this.supplierService.activateDeactivate(id, isActive);
    }
}
