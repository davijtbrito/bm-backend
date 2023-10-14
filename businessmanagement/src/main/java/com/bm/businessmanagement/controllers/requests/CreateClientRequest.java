package com.bm.businessmanagement.controllers.requests;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmRequest;
import com.bm.businessmanagement.dtos.ClientDto;
import com.bm.businessmanagement.dtos.ContactDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientRequest implements BmRequest{

    private String name;
    private String phone;
    private String email;
    

    @Override
    public BmDto getDto() {
        return new ClientDto(
            null,
            this.name,
            new ContactDto(null, email, phone, true),
            true
        );
    }
    
}
