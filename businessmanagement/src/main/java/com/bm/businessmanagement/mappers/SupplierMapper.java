package com.bm.businessmanagement.mappers;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmEntity;
import com.bm.businessmanagement.absctracts.BmMapper;
import com.bm.businessmanagement.absctracts.UseContactDto;
import com.bm.businessmanagement.dtos.ContactDto;
import com.bm.businessmanagement.dtos.SupplierDto;
import com.bm.businessmanagement.entities.SupplierEntity;

@Component
public class SupplierMapper implements BmMapper, UseContactDto{

    private Set<ContactDto> contacts;

    @Override
    public void setContacts(Set<ContactDto> contacts) {
        this.contacts = contacts;
    }

    @Override
    public Set<ContactDto> getContacts() {
        return this.contacts;
    }

    @Override
    public BmDto entityToDto(BmEntity entity) {
        return new SupplierDto(
            ((SupplierEntity) entity).getId(),
            ((SupplierEntity) entity).getName(),
            ((SupplierEntity) entity).getActive(),
            this.contacts
        );
    }

    @Override
    public BmEntity dtoToEntity_forCreation(BmDto dto) {
        return new SupplierEntity(
            null, 
            ((SupplierDto) dto).getName(),
            ((SupplierDto) dto).getActive(),
            LocalDateTime.now(),
            LocalDateTime.now());
    }
    
}
