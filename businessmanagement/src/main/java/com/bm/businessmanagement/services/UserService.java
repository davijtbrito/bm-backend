package com.bm.businessmanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bm.businessmanagement.absctracts.DtoAbstract;
import com.bm.businessmanagement.absctracts.ServiceAbstract;
import com.bm.businessmanagement.dtos.UserDto;
import com.bm.businessmanagement.entities.UserEntity;
import com.bm.businessmanagement.mappers.UserMapper;
import com.bm.businessmanagement.repositories.UserRepository;
import com.bm.businessmanagement.enums.Role;

import lombok.Getter;

@Service
@Getter
public class UserService implements ServiceAbstract{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserMapper userMapper; 

    @Override
    public DtoAbstract create(DtoAbstract dto) {                

        UserDto newUser = (UserDto) userMapper.entityToDto(userRepository.save((UserEntity) userMapper.dtoToEntity_forCreation(dto)));
        return newUser ;
    }

    @Override
    public DtoAbstract update(DtoAbstract dto) {
        return null;
    }

    @Override
    public void activateDeactivate(Long id, boolean isActive) {
        
    }

    @Override
    public List<DtoAbstract> findByName(String keyword) {
        
        List<DtoAbstract> list = new ArrayList<>();
        list.add(userMapper.entityToDto(userRepository.findByName(keyword).get()));

        return list;
    }
    
    public List<DtoAbstract> findByRole(Role role){

        List<UserEntity> entities = userRepository.findByRole(role);
        List<DtoAbstract> dtos = new ArrayList<>();

        entities.stream().forEach(p -> dtos.add(userMapper.entityToDto(p)));

        return dtos;        
    }

    public DtoAbstract changeRole(Long userId, List<Role> roles)
    {
        UserEntity user = this.userRepository.findById(userId).get();
        
        UserEntity userUpdated = this.userRepository.save(new UserEntity(
            user.getId(), 
            user.getEmail(), 
            user.getUsername(), 
            user.getPassword(), 
            roles));            

        return userMapper.entityToDto(userUpdated);
    }
    
}
