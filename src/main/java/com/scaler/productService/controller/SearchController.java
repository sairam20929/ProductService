package com.scaler.productService.controller;

import com.scaler.productService.dto.ProductResponseDTO;
import com.scaler.productService.dto.SearchRequestDto;
import com.scaler.productService.model.Product;
import com.scaler.productService.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Page<Product>> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {

        Page<Product> products = searchService.searchProducts(searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(), searchRequestDto.getPageSize());

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
