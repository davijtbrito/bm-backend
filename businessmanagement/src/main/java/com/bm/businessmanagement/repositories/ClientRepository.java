package com.bm.businessmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.businessmanagement.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
    
}
