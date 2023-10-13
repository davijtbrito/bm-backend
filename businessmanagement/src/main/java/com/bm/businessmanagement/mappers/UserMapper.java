package com.bm.businessmanagement.mappers;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.bm.businessmanagement.absctracts.Dto;
import com.bm.businessmanagement.absctracts.BmEntity;
import com.bm.businessmanagement.absctracts.Mapper;
import com.bm.businessmanagement.dtos.UserDto;
import com.bm.businessmanagement.entities.UserEntity;

@Component
public class UserMapper implements Mapper {            

    @Override
    public Dto entityToDto(BmEntity entity) {
        
        if (Objects.isNull(entity)){
            return null;
        }

        UserEntity userEntity = (UserEntity) entity;

        return new UserDto(
            userEntity.getId(), 
            userEntity.getEmail(), 
            userEntity.getUsername(), 
            userEntity.getPassword(), 
            userEntity.getRoles());

    }

    @Override
    public BmEntity dtoToEntity_forCreation(Dto dto) {
        if (Objects.isNull(dto)){
            return null;
        }

        UserDto userDto = (UserDto) dto;

        return new UserEntity(
            userDto.getId(), 
            userDto.getEmail(), 
            userDto.getUsername(), 
            userDto.getPassword(), 
            userDto.getRoles());
    }
    
}
