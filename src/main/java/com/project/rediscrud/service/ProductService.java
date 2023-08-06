package com.project.rediscrud.service;

import com.project.rediscrud.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void save(ProductDTO productDTO);

    void deleteById(Long id);

    Iterable<ProductDTO> findAll();

    ProductDTO findById(Long id);
}
