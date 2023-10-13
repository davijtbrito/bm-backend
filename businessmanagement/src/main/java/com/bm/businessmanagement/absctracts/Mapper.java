package com.bm.businessmanagement.absctracts;

/*
 * Basic implementations for all mappers.
 */
public interface Mapper {
    
    Dto entityToDto(BmEntity entity);    
    BmEntity dtoToEntity_forCreation(Dto dto);

}
