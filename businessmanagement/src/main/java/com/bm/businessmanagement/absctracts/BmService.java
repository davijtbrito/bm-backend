package com.bm.businessmanagement.absctracts;

import java.util.List;

/*
 * Basic implementations for services that has any type of CRUD.
 */
public interface BmService {

    BmDto create(BmDto dto);
    BmDto update(BmDto dto);
    void activateDeactivate(Long id, boolean isActive);
    List<BmDto> findByName(String keyword);        
}
