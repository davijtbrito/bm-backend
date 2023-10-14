package com.bm.businessmanagement.mappers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmEntity;
import com.bm.businessmanagement.absctracts.BmMapper;
import com.bm.businessmanagement.dtos.ClientDto;
import com.bm.businessmanagement.dtos.ContactDto;
import com.bm.businessmanagement.entities.ClientEntity;
import com.bm.businessmanagement.entities.ContactEntity;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ClientMapper implements BmMapper{

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public BmDto entityToDto(BmEntity entity) {
        
        ClientEntity clientEntity = (ClientEntity) entity;
        Set<ContactEntity> contactsEntity = clientEntity.getContacts();
        Set<ContactDto> contactsDto = new HashSet<ContactDto>();
        contactsEntity.stream().forEach((c) -> {
            contactsDto.add((ContactDto) contactMapper.entityToDto(c));
        });

        return new ClientDto(
            clientEntity.getId(),
            clientEntity.getName(),
            contactsDto,
            clientEntity.getActive()
        );            
    }

    @Override
    public BmEntity dtoToEntity_forCreation(BmDto dto) {

        ClientDto clientDto = (ClientDto) dto;        
        Set<ContactEntity> contactsEntity = new HashSet<ContactEntity>();

        clientDto.getContacts().stream().forEach((c) -> {
            contactsEntity.add((ContactEntity) contactMapper.dtoToEntity_forCreation(c));
        });        

        return new ClientEntity(
            clientDto.getId(),
            clientDto.getName(),
            contactsEntity,
            true,
            LocalDateTime.now(),
            LocalDateTime.now()
        );         
    }    
}
