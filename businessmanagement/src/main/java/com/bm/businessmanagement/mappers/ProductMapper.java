package com.bm.businessmanagement.mappers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmEntity;
import com.bm.businessmanagement.absctracts.BmMapper;
import com.bm.businessmanagement.dtos.ProductDto;
import com.bm.businessmanagement.entities.ProductEntity;

@Component
public class ProductMapper implements BmMapper {

    @Override
    public BmDto entityToDto(BmEntity entity) {
        return new ProductDto(
            ((ProductEntity) entity).getId(),
            ((ProductEntity) entity).getName(),
            ((ProductEntity) entity).getPrice(),
            ((ProductEntity) entity).getImage());
    }

    @Override
    public BmEntity dtoToEntity_forCreation(BmDto dto) {
        
        return new ProductEntity(
            null, 
            ((ProductDto) dto).getName(), 
            ((ProductDto) dto).getPrice(), 
            ((ProductDto) dto).getImage(), 
            true, 
            LocalDateTime.now(), 
            LocalDateTime.now());
    }
    
}
