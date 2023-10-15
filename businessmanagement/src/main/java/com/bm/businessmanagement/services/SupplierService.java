package com.bm.businessmanagement.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmService;
import com.bm.businessmanagement.absctracts.SaveContact;
import com.bm.businessmanagement.dtos.ClientDto;
import com.bm.businessmanagement.dtos.ContactDto;
import com.bm.businessmanagement.dtos.SupplierDto;
import com.bm.businessmanagement.entities.ContactEntity;
import com.bm.businessmanagement.entities.ContactSupplierEntity;
import com.bm.businessmanagement.entities.SupplierEntity;
import com.bm.businessmanagement.mappers.ContactMapper;
import com.bm.businessmanagement.mappers.SupplierMapper;
import com.bm.businessmanagement.repositories.ContactRepository;
import com.bm.businessmanagement.repositories.ContactSupplierRepository;
import com.bm.businessmanagement.repositories.SupplierRepository;

@Service
public class SupplierService implements BmService, SaveContact{

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ContactSupplierRepository contactSupplierRepository;

    @Autowired
    private ContactRepository contactRepository; 

    @Autowired
    private ContactMapper contactMapper; 

    @Override
    public BmDto create(BmDto dto) {
        SupplierDto supplierDto = (SupplierDto) dto;
        SupplierEntity supplierEntity = (SupplierEntity) supplierMapper.dtoToEntity_forCreation(dto);
        supplierEntity = supplierRepository.save(supplierEntity);

        Set<ContactDto> contacts = new HashSet<ContactDto>();

        contacts = this.saveContacts(supplierEntity.getId(), supplierDto.getContacts());
        
        supplierMapper.setContacts(contacts);
        supplierDto = (SupplierDto) supplierMapper.entityToDto(supplierEntity);
        
        return new ClientDto(supplierDto.getId(),
         supplierDto.getName(), 
         supplierDto.getActive(), 
         contacts);       
    }

    @Override
    public BmDto update(BmDto dto) {
        SupplierDto supplierDto = (SupplierDto) dto;
        Optional<SupplierEntity> supplierEntityOpt = supplierRepository.findById(supplierDto.getId());

        if (supplierEntityOpt.isPresent()) {
            
            SupplierEntity updatedSupplier = new SupplierEntity(
                supplierDto.getId(), 
                supplierDto.getName(), 
                supplierDto.getActive(), 
                supplierEntityOpt.get().getDateCreated(), 
                LocalDateTime.now());

            updatedSupplier = (SupplierEntity) supplierRepository.save(updatedSupplier);         
                        
            this.supplierMapper.setContacts((Set<ContactDto>) this.saveContacts(updatedSupplier.getId(), supplierDto.getContacts()));

            supplierDto = (SupplierDto) supplierMapper.entityToDto(updatedSupplier);
                                                
            return supplierDto;  

        }else{
            return null;
        }   
    }

    @Override
    public void activateDeactivate(Long id, boolean isActive) {
        SupplierEntity supplierEntity = this.supplierRepository.findById(id).get();
        
        supplierMapper.entityToDto(supplierRepository.save(new SupplierEntity(
            supplierEntity.getId(), 
            supplierEntity.getName(),
            isActive,             
            supplierEntity.getDateCreated(), 
            LocalDateTime.now())));
    }

    @Override
    public List<BmDto> findByName(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }

    @Override
    public Set<ContactDto> saveContacts(Long idOwnerContacts, Set<ContactDto> contacts) {

        Set<ContactDto> contactsResult = new HashSet<ContactDto>();
            
            contacts.stream().forEach((c) -> {

                if (c.getId() == null){
                    ContactEntity contactEntity = contactRepository.save((ContactEntity) contactMapper.dtoToEntity_forCreation(c));
                    contactSupplierRepository.save(new ContactSupplierEntity(null, idOwnerContacts, contactEntity.getId()));
                    contactsResult.add((ContactDto) contactMapper.entityToDto(contactEntity));

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
                        
                        contactsResult.add((ContactDto) contactMapper.entityToDto(contactUpdated));
                    }                    
                }
                
            });

        return contactsResult;
    }
    
}
