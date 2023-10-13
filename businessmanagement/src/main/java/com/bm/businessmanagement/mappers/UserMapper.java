package com.bm.businessmanagement.mappers;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.bm.businessmanagement.absctracts.DtoAbstract;
import com.bm.businessmanagement.absctracts.EntityAbstract;
import com.bm.businessmanagement.absctracts.MapperAbstract;
import com.bm.businessmanagement.dtos.UserDto;
import com.bm.businessmanagement.entities.UserEntity;

@Component
public class UserMapper implements MapperAbstract {            

    @Override
    public DtoAbstract entityToDto(EntityAbstract entity) {
        
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
    public EntityAbstract dtoToEntity_forCreation(DtoAbstract dto) {
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
