package com.bm.businessmanagement.dtos;

import java.util.Set;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.UseContactDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SupplierDto implements BmDto, UseContactDto {
    
    private Long id;
    private String name;
    private Boolean active;
    private Set<ContactDto> contacts;
    
    @Override
    public void setContacts(Set<ContactDto> contacts) {
        this.contacts = contacts;
    }
}
