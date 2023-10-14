package com.bm.businessmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.businessmanagement.entities.ContactClientEntity;

public interface ContactClientRepository extends JpaRepository<ContactClientEntity, Long> {
    
}
