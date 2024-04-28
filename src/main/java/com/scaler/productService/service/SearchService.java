package com.scaler.productService.service;

import com.scaler.productService.model.Product;
import com.scaler.productService.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final ProductRepository productRepo;

    public SearchService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public Product searchProduct(String query) {
        return productRepo.findByTitleEquals(query);
    }

    public Page<Product> searchProducts(String query, int pageNumber, int pageSize) {

        Sort sort = Sort.by("price").and(Sort.by("id")).descending();

        return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber, pageSize, sort));
    }
}
