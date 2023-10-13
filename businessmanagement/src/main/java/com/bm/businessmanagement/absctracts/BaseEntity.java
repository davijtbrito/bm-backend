package com.bm.businessmanagement.absctracts;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Apply basic metadatas for entities+++
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements BmEntity{

    protected Boolean active;// this metadata should be seeing only in database context
    protected LocalDateTime dateCreated;// this metadata should be seeing only in database context
    protected LocalDateTime dateUpdated;// this metadata should be seeing only in database context
}

