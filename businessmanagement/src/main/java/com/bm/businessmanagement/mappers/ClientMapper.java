package com.bm.businessmanagement.mappers;

import java.time.LocalDateTime;

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
        ContactEntity contactEntity = clientEntity.getContact();
        ContactDto contactDto = (ContactDto) contactMapper.entityToDto(contactEntity);

        return new ClientDto(
            clientEntity.getId(),
            clientEntity.getName(),
            contactDto,
            clientEntity.getActive()
        );            
    }

    @Override
    public BmEntity dtoToEntity_forCreation(BmDto dto) {

        ClientDto clientDto = (ClientDto) dto;
        ContactDto contactDto = clientDto.getContact();
        ContactEntity contactEntity = (ContactEntity) contactMapper.dtoToEntity_forCreation(contactDto);

        return new ClientEntity(
            clientDto.getId(),
            clientDto.getName(),
            contactEntity,
            true,
            LocalDateTime.now(),
            LocalDateTime.now()
        );         
    }    
}
