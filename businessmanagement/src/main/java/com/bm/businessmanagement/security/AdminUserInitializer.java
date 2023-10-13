package com.bm.businessmanagement.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bm.businessmanagement.absctracts.DtoAbstract;
import com.bm.businessmanagement.dtos.UserDto;
import com.bm.businessmanagement.enums.Role;
import com.bm.businessmanagement.security.UserDetailsServiceImpl;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) {

        List<DtoAbstract> dtos = new ArrayList<>();
        dtos = userService.getUserService().findByRole(Role.ADMIN);

        if (dtos.isEmpty()) {
            
            List<Role> roles = new ArrayList<>();
            roles.add(Role.ADMIN);            
            
            userService.getUserService().create(new UserDto(
                null, 
                "davibrito@gmail.com", 
                "davi", 
                encoder.encode("davi123"), 
                roles));            
        }
    }
}

