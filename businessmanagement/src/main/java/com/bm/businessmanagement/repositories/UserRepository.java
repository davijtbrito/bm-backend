package com.bm.businessmanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bm.businessmanagement.entities.UserEntity;
import com.bm.businessmanagement.enums.Role;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.username) = LOWER(:keyword)")
    Optional<UserEntity> findByName(String keyword);

    @Query("SELECT u FROM UserEntity u WHERE :role MEMBER OF u.roles")
    public List<UserEntity> findByRole(Role role);
}
