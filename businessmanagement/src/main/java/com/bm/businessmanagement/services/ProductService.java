package com.bm.businessmanagement.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bm.businessmanagement.absctracts.BmDto;
import com.bm.businessmanagement.absctracts.BmService;
import com.bm.businessmanagement.dtos.ProductDto;
import com.bm.businessmanagement.entities.ProductEntity;
import com.bm.businessmanagement.mappers.ProductMapper;
import com.bm.businessmanagement.repositories.ProductRepository;

@Service
public class ProductService implements BmService{
   
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional
    public BmDto create(BmDto dto) {
        ProductDto productDto = (ProductDto) dto;
        ProductEntity productCreated = this.productRepository.save((ProductEntity) this.productMapper.dtoToEntity_forCreation(productDto));

        return this.productMapper.entityToDto(productCreated);
    }

    @Override
    @Transactional
    public BmDto update(BmDto dto) {
        ProductDto productDto = (ProductDto) dto;
        Optional<ProductEntity> entityOpt = this.productRepository.findById(productDto.getId());

        if (entityOpt.isPresent()){
            return this.productMapper.entityToDto(
                    this.productRepository.save(new ProductEntity(
                    entityOpt.get().getId(),
                    entityOpt.get().getName(),
                    entityOpt.get().getPrice(),
                    entityOpt.get().getImage(),
                    true,
                    LocalDateTime.now(),
                    LocalDateTime.now()
                ))
            );
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void activateDeactivate(Long id, boolean isActive) {
        Optional<ProductEntity> entityOpt = this.productRepository.findById(id);

        if (entityOpt.isPresent()){
            this.productRepository.save(new ProductEntity(
                entityOpt.get().getId(),
                entityOpt.get().getName(),
                entityOpt.get().getPrice(),
                entityOpt.get().getImage(),
                true,
                entityOpt.get().getDateCreated(),
                LocalDateTime.now()));
        }
    }

    @Override
    public List<BmDto> findByName(String keyword) {
        List<ProductEntity> entities = this.productRepository.findByName(keyword);
        List<BmDto> products = new ArrayList<>();

        entities.stream().forEach((p) -> {
            products.add(this.productMapper.entityToDto(p));
        });
                
        return products;
    }
    
}
