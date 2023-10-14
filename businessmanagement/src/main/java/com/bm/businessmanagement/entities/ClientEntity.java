package com.bm.businessmanagement.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bm.businessmanagement.absctracts.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class ClientEntity extends BaseEntity {
    
    public ClientEntity(Long id, String name, ContactEntity contact, Boolean active, 
    LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        
        super(active, dateCreated, dateUpdated);
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    private ContactEntity contact;
}
