package com.bm.businessmanagement.dtos;

import com.bm.businessmanagement.absctracts.BmDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContactDto implements BmDto{
    
    private Long id;
    private String phone;
    private String email;
}
