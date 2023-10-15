package com.bm.businessmanagement.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bm.businessmanagement.absctracts.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity extends BaseEntity{

    public ProductEntity(Long id, String name, Double price, byte[] image, Boolean active, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        super(active, dateCreated, dateUpdated);
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private byte[] image;
}
