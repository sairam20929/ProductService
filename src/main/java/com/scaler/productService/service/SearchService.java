package com.scaler.productService.service;

import com.scaler.productService.model.Product;
import com.scaler.productService.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private ProductRepository productRepo;

    public SearchService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public Product searchProduct(String query) {
        return productRepo.findByTitleEquals(query);
    }
}
