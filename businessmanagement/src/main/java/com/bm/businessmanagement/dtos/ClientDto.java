package com.bm.businessmanagement.dtos;

import java.util.Set;

import com.bm.businessmanagement.absctracts.BmDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientDto implements BmDto{

    private Long id;
    private String name;
    private Boolean active;
    private Set<ContactDto> contacts;
}
