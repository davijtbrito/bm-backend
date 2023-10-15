package com.bm.businessmanagement.absctracts;

import java.util.Set;

import com.bm.businessmanagement.dtos.ContactDto;

public interface SaveContact {
    
    Set<ContactDto> saveContacts(Long idOwnerContacts, Set<ContactDto> contacts);
}
