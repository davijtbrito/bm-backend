package com.bm.businessmanagement.mappers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmEntity;
import com.bm.businessmanagement.absctracts.BmMapper;
import com.bm.businessmanagement.dtos.ClientDto;
import com.bm.businessmanagement.entities.ClientEntity;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ClientMapper implements BmMapper{


    @Override
    public BmDto entityToDto(BmEntity entity) {
        
        ClientEntity clientEntity = (ClientEntity) entity;

        return new ClientDto(
            clientEntity.getId(),
            clientEntity.getName(),
            clientEntity.getActive()
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
}
