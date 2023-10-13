package com.bm.businessmanagement.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bm.businessmanagement.controllers.requests.ChangeRolesUserRequest;
import com.bm.businessmanagement.controllers.requests.CreateUserRequest;
import com.bm.businessmanagement.controllers.requests.JwtRequest;
import com.bm.businessmanagement.controllers.responses.JwtResponse;
import com.bm.businessmanagement.dtos.UserDto;
import com.bm.businessmanagement.services.AuthenticationService;
import com.bm.businessmanagement.services.UserService;
import com.bm.businessmanagement.utils.Messages;


@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/jwt")
    public ResponseEntity<Object> getJwt(@RequestBody JwtRequest request) {

        try {                        
            return ResponseEntity.ok(new JwtResponse(authenticationService.getJwt(request.getUserName(), request.getPassword())));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }

    @PostMapping("/create-user")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> createUser(@RequestBody CreateUserRequest request) {

        try {                        
            UserDto newUser = (UserDto) request.getDto();
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            
            return ResponseEntity.ok(userService.create(newUser));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }        
    }    

    @PostMapping("/change-role")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> changeRole(@RequestBody ChangeRolesUserRequest request) {

        try {                        
            if (authenticationService.changeRole(request.getUserId(), request.getRoles())){

                return ResponseEntity.ok(Messages.OPERATION_OK);
            }

            return ResponseEntity.ok(Messages.OPERATION_ERROR);            
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }        
    }    
}

