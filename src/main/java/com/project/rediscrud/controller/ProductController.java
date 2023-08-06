package com.project.rediscrud.controller;

import com.project.rediscrud.dto.ProductDTO;
import com.project.rediscrud.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<Iterable<ProductDTO>> findAll(){
        Iterable<ProductDTO> products=productService.findAll();
        HttpHeaders headers=new HttpHeaders();
        headers.add("X-Message","Ürünler Listelendi!");
        return ResponseEntity.ok().headers(headers).body(products);

    }
    @Cacheable(value = "product", key = "#id")
    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody ProductDTO productDTO){
        productDTO.setId(0L);
        productService.save(productDTO);
    }

    @PutMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @CachePut(value = "product", key = "#productDTO.id")
    public void update(@RequestBody ProductDTO productDTO){
        productService.save(productDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @CacheEvict(value = "product", key="#id")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

}
