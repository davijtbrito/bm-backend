package com.bm.businessmanagement.controllers.requests;

import java.util.HashSet;
import java.util.Set;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmRequest;
import com.bm.businessmanagement.dtos.ContactDto;
import com.bm.businessmanagement.dtos.SupplierDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplierRequest implements BmRequest{
    
    private String name;
    private String phone;
    private String email;
    @Override
    public BmDto getDto() {

        Set<ContactDto> contactDtos = new HashSet<>();
        contactDtos.add(new ContactDto(null, phone, email));

        return new SupplierDto(null, name, true, contactDtos);
    }
}
