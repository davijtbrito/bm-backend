package com.bm.businessmanagement.controllers.requests;


import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmRequest;
import com.bm.businessmanagement.dtos.ClientDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientRequest implements BmRequest{
    
    private Long id;
    private String name;

    @Override
    public BmDto getDto() {
        return new ClientDto(id, name, true);
    }
}
