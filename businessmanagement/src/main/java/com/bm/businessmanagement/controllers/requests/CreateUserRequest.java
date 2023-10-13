package com.bm.businessmanagement.controllers.requests;

import java.util.ArrayList;
import java.util.List;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmRequest;
import com.bm.businessmanagement.dtos.UserDto;
import com.bm.businessmanagement.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest implements BmRequest {

    private String email;
    private String username;
    private String password;

    @Override
    public BmDto getDto() {

        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        
        return new UserDto(            
            null, 
            email, 
            username, 
            password, 
            roles);
    }
}
