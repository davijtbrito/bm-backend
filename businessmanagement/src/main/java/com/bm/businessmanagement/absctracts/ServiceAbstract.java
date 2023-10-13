package com.bm.businessmanagement.absctracts;

import java.util.List;

/*
 * Basic implementations for services that has any type of CRUD.
 */
public interface ServiceAbstract {

    Dto create(Dto dto);
    Dto update(Dto dto);
    void activateDeactivate(Long id, boolean isActive);
    List<Dto> findByName(String keyword);        
}
