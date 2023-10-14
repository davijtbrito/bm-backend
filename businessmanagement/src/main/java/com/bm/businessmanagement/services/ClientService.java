package com.bm.businessmanagement.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmService;
import com.bm.businessmanagement.dtos.ClientDto;
import com.bm.businessmanagement.dtos.ContactDto;
import com.bm.businessmanagement.entities.ClientEntity;
import com.bm.businessmanagement.entities.ContactClientEntity;
import com.bm.businessmanagement.entities.ContactEntity;
import com.bm.businessmanagement.mappers.ClientMapper;
import com.bm.businessmanagement.repositories.ClientRepository;
import com.bm.businessmanagement.repositories.ContactClientRepository;
import com.bm.businessmanagement.repositories.ContactRepository;

@Service
public class ClientService implements BmService{

    @Autowired
    private ClientRepository repository;

    @Autowired  
    private ContactClientRepository contactClientRepository;

    @Autowired
    private ClientMapper clientMapper;
    
    @Autowired
    private com.bm.businessmanagement.mappers.ContactMapper contactMapper;

    @Autowired
    private ContactRepository contactRepository;

    @Override        
    @Transactional
    public BmDto create(BmDto dto) {        
        
        ClientDto clientDto = (ClientDto) dto;
        ClientEntity clientEntity = (ClientEntity) clientMapper.dtoToEntity_forCreation(dto);
        clientEntity = repository.save(clientEntity);
        Long idClient = clientEntity.getId(); 

        Set<ContactDto> contacts = new HashSet<ContactDto>();

        clientDto.getContacts().stream().forEach((c) -> {
            ContactEntity contactEntity = contactRepository.save((ContactEntity) contactMapper.dtoToEntity_forCreation(c));
            contactClientRepository.save(new ContactClientEntity(null, idClient, contactEntity.getId()));
            contacts.add((ContactDto) contactMapper.entityToDto(contactEntity));            
        });
        
        clientDto = (ClientDto) clientMapper.entityToDto(clientEntity);
        
        return new ClientDto(idClient,
         clientDto.getName(), 
         clientDto.getActive(), 
         contacts);        
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
