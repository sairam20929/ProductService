package com.scaler.productService.controller;

import com.scaler.productService.dto.ProductResponseDTO;
import com.scaler.productService.model.Product;
import com.scaler.productService.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.scaler.productService.mapper.ProductMapper.getProductResponseDTOFromProduct;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/{productTitle}")
    public ResponseEntity<ProductResponseDTO> searchProduct(@PathVariable("productTitle") String query) {

        ProductResponseDTO productResponseDTO =
                getProductResponseDTOFromProduct(searchService.searchProduct(query));
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }
}
