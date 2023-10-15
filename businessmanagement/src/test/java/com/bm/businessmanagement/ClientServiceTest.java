package com.bm.businessmanagement;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.bm.businessmanagement.dtos.ClientDto;
import com.bm.businessmanagement.dtos.ContactDto;
import com.bm.businessmanagement.entities.ClientEntity;
import com.bm.businessmanagement.entities.ContactEntity;
import com.bm.businessmanagement.mappers.ClientMapper;
import com.bm.businessmanagement.mappers.ContactMapper;
import com.bm.businessmanagement.repositories.ClientRepository;
import com.bm.businessmanagement.repositories.ContactClientRepository;
import com.bm.businessmanagement.repositories.ContactRepository;
import com.bm.businessmanagement.services.ClientService;

@SpringBootTest
@Transactional
@SpringJUnitConfig
public class ClientServiceTest {
    
    @Autowired
    private ClientService clientService;

    @InjectMocks
    private ClientService clientService2;       
    
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactClientRepository contactClientRepository;

    @Mock
    private ContactMapper contactMapper;

    @Mock
    private ClientMapper clientMapper;

	@Test
	void contextLoads() {
	}

    @Test
    public void testCreateClient(){
        Set<ContactDto> contacts = new HashSet<>();
        contacts.add(new ContactDto(null, "46548979", "email@test"));
        ClientDto newCLient = new ClientDto(null, "TestName", true, contacts);
        ClientDto insertedCLient = (ClientDto) this.clientService.create(newCLient);

        assertTrue(insertedCLient.getId() != null);
        assertTrue(insertedCLient.getName() == "TestName");        
        insertedCLient.getContacts().stream().forEach((c) -> {
            contacts.stream().forEach((p) -> {
                assertTrue(p.getPhone().equals(c.getPhone()));
                assertTrue(p.getEmail().equals(c.getEmail()));
            });
        });
    }

    @Test
    public void testUpdateClient(){
        
        ContactEntity contactEntity = new ContactEntity(1L, "46548979", "email@test", true, LocalDateTime.now(), LocalDateTime.now());
        ContactDto contactDto = new ContactDto(null, "46548979", "email@test");
        Set<ContactEntity> contactsEntity = new HashSet<>();
        contactsEntity.add(contactEntity);

        Set<ContactDto> contacts = new HashSet<>();
        contacts.add(contactDto);

        ClientEntity clientEntity = new ClientEntity(1L, "name", true, LocalDateTime.now(), LocalDateTime.now());
        ClientDto clientDto = new ClientDto(1L, "updatedName", true, contacts);
                
        when(this.clientRepository.findById(any())).thenReturn(Optional.of(clientEntity));       
        when(this.contactRepository.save(any())).thenReturn(contactEntity);
        when(this.clientRepository.save(any())).thenReturn(clientEntity);
        when(this.contactMapper.dtoToEntity_forCreation(any())).thenReturn(contactEntity);
        when(this.contactMapper.entityToDto(any())).thenReturn(contactDto);
        when(this.clientMapper.entityToDto(any())).thenReturn(clientDto);

        assertTrue(((ClientDto) this.clientService2.update(clientDto)).getName().equals("updatedName"));        
    }
}
