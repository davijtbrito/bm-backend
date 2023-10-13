package com.bm.businessmanagement.absctracts;

import java.util.List;

/*
 * Basic implementations for services that has any type of CRUD.
 */
public interface ServiceAbstract {

    DtoAbstract create(DtoAbstract dto);
    DtoAbstract update(DtoAbstract dto);
    void activateDeactivate(Long id, boolean isActive);
    List<DtoAbstract> findByName(String keyword);        
}
