package com.bm.businessmanagement.controllers.requests;

import java.util.List;

import com.bm.businessmanagement.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRolesUserRequest {

    private Long userId;
    private List<Role> roles;
}
