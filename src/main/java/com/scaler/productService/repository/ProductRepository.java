package com.scaler.productService.repository;

import com.scaler.productService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductId(Long id);
    List<Product> findAll();
    Product save(Product product);
}
