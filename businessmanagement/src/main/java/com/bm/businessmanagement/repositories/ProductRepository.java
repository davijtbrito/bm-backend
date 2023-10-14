package com.bm.businessmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.businessmanagement.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    
}
