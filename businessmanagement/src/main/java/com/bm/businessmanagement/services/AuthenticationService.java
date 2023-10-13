package com.bm.businessmanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.bm.businessmanagement.security.JwtTokenProvider;
import com.bm.businessmanagement.enums.Role;
import com.bm.businessmanagement.security.UserDetailsServiceImpl;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;   
    
    @Autowired
    private UserDetailsService userDetailsService;

    public String getJwt(String username, String password){

        UserDetails details = userDetailsService.loadUserByUsername(username);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, details.getAuthorities()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();            

        List<Role> roles = new ArrayList<>();
        details.getAuthorities().stream().forEach(p -> roles.add(Role.fromValue(p.getAuthority())));
        
        return JwtTokenProvider.generateToken(userDetails.getUsername(), roles);

    }

    public boolean changeRole(Long userId, List<Role> roles){

        try {
            ((UserDetailsServiceImpl) userDetailsService).getUserService().changeRole(userId, roles);
            return true;            
        } catch (Exception e) {
            return false;
        }        
    }

    public Role getRole(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {

            return Role.ADMIN;
        } else if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {

            return Role.USER;
        }        

        return Role.NONE;
    }
    
}
