package com.project.rediscrud.service;

import com.project.rediscrud.dto.ProductDTO;
import com.project.rediscrud.entity.Product;
import com.project.rediscrud.mapper.ModelMapperService;
import com.project.rediscrud.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapperService modelMapperService;


    @Override
    public void save(ProductDTO productDTO) {
        Product product=modelMapperService.getModelMapper()
                .map(productDTO,Product.class);
        productRepository.save(product);

    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Iterable<ProductDTO> findAll() {
        Iterable<Product> products=productRepository.findAll();
        Stream<Product> productStream = StreamSupport.stream(products.spliterator(), false);
        List<ProductDTO> productDTO = productStream
                .map(product -> this.modelMapperService.getModelMapper().map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return productDTO;
    }


    @Override
    public ProductDTO findById(Long id) {
    Product product=productRepository.findById(id).orElseThrow(
            ()->new NoSuchElementException("Berlitilen ID'ye sahip ürün bulunamadı!"));
    ProductDTO productDTO=modelMapperService.getModelMapper()
            .map(product,ProductDTO.class);
    return productDTO;

    }
}
