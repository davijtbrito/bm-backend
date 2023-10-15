package com.bm.businessmanagement.dtos;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmProductDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto implements BmDto, BmProductDto {
    
    private Long id;
    private String name;
    private Double price;
    private byte[] image;
}
