package com.bm.businessmanagement.absctracts;

/*
 * Basic implementations for all mappers.
 */
public interface MapperAbstract {
    
    DtoAbstract entityToDto(EntityAbstract entity);    
    EntityAbstract dtoToEntity_forCreation(DtoAbstract dto);

}
