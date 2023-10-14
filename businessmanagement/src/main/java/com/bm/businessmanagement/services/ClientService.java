package com.bm.businessmanagement.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmService;
import com.bm.businessmanagement.dtos.ClientDto;
import com.bm.businessmanagement.entities.ClientEntity;
import com.bm.businessmanagement.mappers.ClientMapper;
import com.bm.businessmanagement.repositories.ClientRepository;

@Service
public class ClientService implements BmService{

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public BmDto create(BmDto dto) {        

        ClientEntity clientEntity = (ClientEntity) clientMapper.dtoToEntity_forCreation(dto);

        return clientMapper.entityToDto(repository.save(clientEntity));
    }

    @Override
    public BmDto update(BmDto dto) {
        ClientEntity clientEntity = this.repository.findById(((ClientDto) dto).getId()).get();
        
        return clientMapper.entityToDto(repository.save(new ClientEntity(
            clientEntity.getId(), 
            clientEntity.getName(),
            clientEntity.getActive(), 
            clientEntity.getDateCreated(), 
            LocalDateTime.now())));
    }

    @Override
    public void activateDeactivate(Long id, boolean isActive) {
         ClientEntity clientEntity = this.repository.findById(id).get();
        
        clientMapper.entityToDto(repository.save(new ClientEntity(
            clientEntity.getId(), 
            clientEntity.getName(),
            isActive, 
            clientEntity.getDateCreated(), 
            LocalDateTime.now())));
    }

    @Override
    public List<BmDto> findByName(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }
    
}
