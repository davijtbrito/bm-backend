package com.bm.businessmanagement.dtos;

import com.bm.businessmanagement.absctracts.BmDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientDto implements BmDto{

    private Long id;
    private String name;
    private ContactDto contact;
    private Boolean active;
}
