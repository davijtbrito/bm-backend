package com.bm.businessmanagement.security;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bm.businessmanagement.dtos.UserDto;
import com.bm.businessmanagement.services.UserService;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {    

    @Getter    
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserDto dto = (UserDto) userService.findByName(username).get(0);

        List<GrantedAuthority> authorities = dto.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());        

        return new User(
            dto.getUsername(), 
            dto.getPassword(), 
            authorities);                
    }
}

