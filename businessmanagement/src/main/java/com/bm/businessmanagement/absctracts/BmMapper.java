package com.bm.businessmanagement.absctracts;

/*
 * Basic implementations for all mappers.
 */
public interface BmMapper {
    
    BmDto entityToDto(BmEntity entity);    
    BmEntity dtoToEntity_forCreation(BmDto dto);

}
