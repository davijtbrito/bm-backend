package com.bm.businessmanagement.mappers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmEntity;
import com.bm.businessmanagement.absctracts.BmMapper;
import com.bm.businessmanagement.dtos.ContactDto;
import com.bm.businessmanagement.entities.ContactEntity;

@Component
public class ContactMapper implements BmMapper {

    @Override
    public BmDto entityToDto(BmEntity entity) {
        return new ContactDto(
            ((ContactEntity) entity).getId(),
            ((ContactEntity) entity).getEmail(),
            ((ContactEntity) entity).getPhone(),
            ((ContactEntity) entity).getActive()
        );
    }

    @Override
    public BmEntity dtoToEntity_forCreation(BmDto dto) {
        return new ContactEntity(
            ((ContactDto) dto).getId(),
            ((ContactDto) dto).getEmail(),
            ((ContactDto) dto).getPhone(),
            true,
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }
        
    public BmEntity dtoToEntity(BmDto dto) {
        return new ContactEntity(
            ((ContactDto) dto).getId(),
            ((ContactDto) dto).getEmail(),
            ((ContactDto) dto).getPhone(),
            ((ContactDto) dto).getActive(),
            null,
            LocalDateTime.now()
        );
    }
    
}
