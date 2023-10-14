package com.bm.businessmanagement.dtos;

import java.util.List;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements BmDto{
    
    private Long id;
    private String email;
    private String username;
    private String password;
    private List<Role> roles;
}
