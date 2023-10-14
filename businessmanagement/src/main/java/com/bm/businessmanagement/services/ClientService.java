package com.bm.businessmanagement.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

        Set<ContactDto> contacts = new HashSet<ContactDto>();

        Long idClient = clientDto.getId();
        clientDto.getContacts().stream().forEach((c) -> {
            ContactEntity contactEntity = contactRepository.save((ContactEntity) contactMapper.dtoToEntity_forCreation(c));
            contactClientRepository.save(new ContactClientEntity(null, idClient, contactEntity.getId()));
            contacts.add((ContactDto) contactMapper.entityToDto(contactEntity));            
        });
        
        clientDto = (ClientDto) clientMapper.entityToDto(clientEntity);
        
        return new ClientDto(clientDto.getId(),
         clientDto.getName(), 
         clientDto.getActive(), 
         contacts);        
    }

    @Override
    @Transactional
    public BmDto update(BmDto dto) {

        ClientDto clientDto = (ClientDto) dto;
        Optional<ClientEntity> clientEntity = repository.findById(clientDto.getId());

        if (clientEntity.isPresent()) {
            
            ClientEntity updatedClient = new ClientEntity(
                clientEntity.get().getId(), 
                clientDto.getName(), 
                clientDto.getActive(), 
                clientEntity.get().getDateCreated(), 
                LocalDateTime.now());

            updatedClient = (ClientEntity) repository.save(updatedClient);         

            Set<ContactDto> contacts = this.saveContacts(clientDto);
            clientDto = (ClientDto) clientMapper.entityToDto(updatedClient);
                                                
            return new ClientDto(clientDto.getId(), clientDto.getName(), clientDto.getActive(), contacts);  

        }else{
            return null;
        }   

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

    private Set<ContactDto> saveContacts(ClientDto clientDto){

        Set<ContactDto> contacts = new HashSet<ContactDto>();

            Long idClient = clientDto.getId();
            clientDto.getContacts().stream().forEach((c) -> {

                if (c.getId() == null){
                    ContactEntity contactEntity = contactRepository.save((ContactEntity) contactMapper.dtoToEntity_forCreation(c));
                    contactClientRepository.save(new ContactClientEntity(null, idClient, contactEntity.getId()));
                    contacts.add((ContactDto) contactMapper.entityToDto(contactEntity));

                }else{
                    Optional<ContactEntity> contactEntityOpt = contactRepository.findById(c.getId());
                    if(contactEntityOpt.isPresent()){
                        ContactEntity contactUpdated = contactRepository.save(new ContactEntity(
                            c.getId(), 
                            c.getEmail(), 
                            c.getPhone(), 
                            true,
                            contactEntityOpt.get().getDateCreated(), 
                            LocalDateTime.now()));                       
                        
                        contacts.add((ContactDto) contactMapper.entityToDto(contactUpdated));
                    }                    
                }
                
            });

        return contacts;
    }
    
}
