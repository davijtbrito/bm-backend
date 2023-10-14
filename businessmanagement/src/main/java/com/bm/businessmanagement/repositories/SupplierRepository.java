package com.bm.businessmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.businessmanagement.entities.SupplierEntity;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>{
    
}
