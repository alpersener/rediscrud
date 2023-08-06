package com.project.rediscrud.repository;

import com.project.rediscrud.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
