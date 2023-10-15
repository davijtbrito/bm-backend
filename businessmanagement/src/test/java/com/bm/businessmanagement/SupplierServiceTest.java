package com.bm.businessmanagement;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

import com.bm.businessmanagement.dtos.ContactDto;
import com.bm.businessmanagement.dtos.SupplierDto;
import com.bm.businessmanagement.entities.ContactEntity;
import com.bm.businessmanagement.entities.SupplierEntity;
import com.bm.businessmanagement.mappers.ContactMapper;
import com.bm.businessmanagement.mappers.SupplierMapper;
import com.bm.businessmanagement.repositories.ContactRepository;
import com.bm.businessmanagement.repositories.ContactSupplierRepository;
import com.bm.businessmanagement.repositories.SupplierRepository;
import com.bm.businessmanagement.services.SupplierService;

@SpringBootTest
@Transactional
@SpringJUnitConfig
public class SupplierServiceTest {
    
    @Autowired
    private SupplierService supplierService;

    @InjectMocks
    private SupplierService supplierService2;
    
    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactSupplierRepository contactSupplierRepository;

    @Mock
    private ContactMapper contactMapper;

    @Mock
    private SupplierMapper supplierMapper;

    @Test
	void contextLoads() {
	}

    @Test
    public void testCreateSupplier(){
        Set<ContactDto> contacts = new HashSet<>();
        contacts.add(new ContactDto(null, "46548979", "email@test"));
        SupplierDto newSupplier = new SupplierDto(null, "TestName", true, contacts);
        SupplierDto insertedSupplier = (SupplierDto) this.supplierService.create(newSupplier);

        assertTrue(insertedSupplier.getId() != null);
        assertTrue(insertedSupplier.getName() == "TestName");        
        insertedSupplier.getContacts().stream().forEach((c) -> {
            contacts.stream().forEach((p) -> {
                assertTrue(p.getPhone().equals(c.getPhone()));
                assertTrue(p.getEmail().equals(c.getEmail()));
            });
        });
    }

    @Test
    public void testUpdateUpdate(){
        
        ContactEntity contactEntity = new ContactEntity(1L, "46548979", "email@test", true, LocalDateTime.now(), LocalDateTime.now());
        ContactDto contactDto = new ContactDto(null, "46548979", "email@test");
        Set<ContactEntity> contactsEntity = new HashSet<>();
        contactsEntity.add(contactEntity);

        Set<ContactDto> contacts = new HashSet<>();
        contacts.add(contactDto);

        SupplierEntity supplierEntity = new SupplierEntity(1L, "name", true, LocalDateTime.now(), LocalDateTime.now());
        SupplierDto supplierDto = new SupplierDto(1L, "updatedName", true, contacts);
                
        when(this.supplierRepository.findById(any())).thenReturn(Optional.of(supplierEntity));       
        when(this.contactRepository.save(any())).thenReturn(contactEntity);
        when(this.supplierRepository.save(any())).thenReturn(supplierEntity);
        when(this.contactMapper.dtoToEntity_forCreation(any())).thenReturn(contactEntity);
        when(this.contactMapper.entityToDto(any())).thenReturn(contactDto);
        when(this.supplierMapper.entityToDto(any())).thenReturn(supplierDto);

        assertTrue(((SupplierDto) this.supplierService2.update(supplierDto)).getName().equals("updatedName"));        
    }
}
