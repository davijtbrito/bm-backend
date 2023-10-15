package com.bm.businessmanagement.mappers;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmEntity;
import com.bm.businessmanagement.absctracts.BmMapper;
import com.bm.businessmanagement.absctracts.UseContactDto;
import com.bm.businessmanagement.dtos.ClientDto;
import com.bm.businessmanagement.dtos.ContactDto;
import com.bm.businessmanagement.entities.ClientEntity;

@Component
public class ClientMapper implements BmMapper, UseContactDto{
    

    private Set<ContactDto> contacts;

    @Override
    public BmDto entityToDto(BmEntity entity) {
        
        ClientEntity clientEntity = (ClientEntity) entity;

        return new ClientDto(
            clientEntity.getId(),
            clientEntity.getName(),
            clientEntity.getActive(),
            this.contacts
        );            
    }

    @Override
    public BmEntity dtoToEntity_forCreation(BmDto dto) {

        ClientDto clientDto = (ClientDto) dto;    

        return new ClientEntity(
            clientDto.getId(),
            clientDto.getName(),
            true,            
            LocalDateTime.now(),
            LocalDateTime.now()
        );         
    }

    @Override
    public void setContacts(Set<ContactDto> contacts) {
        this.contacts = contacts;
    }

    @Override
    public Set<ContactDto> getContacts() {
        return this.contacts;
    }        
}
