package com.bm.businessmanagement.controllers.requests;

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
public class UpdateSupplierRequest implements BmRequest {

    private Long id;
    private String name;
    private Set<ContactDto> contacts;

    @Override
    public BmDto getDto() {                

        return new SupplierDto(id, name, true, contacts);
    }
    
}
