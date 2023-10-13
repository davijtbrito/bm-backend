package com.bm.businessmanagement.mappers;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmEntity;
import com.bm.businessmanagement.absctracts.BmMapper;
import com.bm.businessmanagement.dtos.UserDto;
import com.bm.businessmanagement.entities.UserEntity;

@Component
public class UserMapper implements BmMapper {            

    @Override
    public BmDto entityToDto(BmEntity entity) {
        
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
    public BmEntity dtoToEntity_forCreation(BmDto dto) {
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
