package com.bm.businessmanagement.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bm.businessmanagement.absctracts.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contact")
public class ContactEntity extends BaseEntity {
    
    
    public ContactEntity(Long id, String email,
            String phone, Boolean active, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
                
        super(active, dateCreated, dateUpdated);
        this.id = id;
        this.email = email;
        this.phone = phone;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String phone;
}
