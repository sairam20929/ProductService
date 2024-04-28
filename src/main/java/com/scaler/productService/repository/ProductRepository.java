package com.scaler.productService.repository;

import com.scaler.productService.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    Product findByTitleEquals(String title);

    Page<Product> findByTitleEquals(String query, Pageable pageable);
}
