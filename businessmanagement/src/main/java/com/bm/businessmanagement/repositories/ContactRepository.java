package com.bm.businessmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.businessmanagement.entities.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    
}
