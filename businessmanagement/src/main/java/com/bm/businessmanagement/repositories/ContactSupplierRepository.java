package com.bm.businessmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.businessmanagement.entities.ContactSupplierEntity;

public interface ContactSupplierRepository extends JpaRepository<ContactSupplierEntity, Long>{
    
}
