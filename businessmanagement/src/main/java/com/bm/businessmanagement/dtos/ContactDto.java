package com.bm.businessmanagement.dtos;

import com.bm.businessmanagement.absctracts.BmDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ContactDto implements BmDto {
    
    private Long id;
    private String email;
    private String phone;
    private Boolean active;
}
