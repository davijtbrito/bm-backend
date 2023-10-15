package com.bm.businessmanagement.absctracts;

import java.util.Set;

import com.bm.businessmanagement.dtos.ContactDto;

public interface UseContactDto {
    
    void setContacts(Set<ContactDto> contacts);
    Set<ContactDto> getContacts();
}
