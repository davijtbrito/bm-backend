package com.bm.businessmanagement.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    
    public ClientEntity(Long id, String name, Set<ContactEntity> contacts, Boolean active, 
    LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        
        super(active, dateCreated, dateUpdated);
        this.id = id;
        this.name = name;
        this.contacts = contacts;        
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContactEntity> contacts = new HashSet<ContactEntity>();
}
