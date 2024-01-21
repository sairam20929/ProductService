package com.scaler.productService.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.productService.dto.ProductRequestDTO;
import com.scaler.productService.dto.ProductResponseDTO;
import com.scaler.productService.mapper.ProductMapper;
import com.scaler.productService.model.Product;
import com.scaler.productService.service.IProductService;

/**
 * The Class ProductController.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    /**
     * The product service.
     */
    private final IProductService productService;

    /**
     * Instantiates a new product controller.
     *
     * @param productService the product service
     */
    public ProductController(@Qualifier("selfProductService") IProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets the product by id.
     *
     * @param productId the product id
     * @return the product by id
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("productId") Long productId) {

        try {
            Product productById = productService.getProductById(productId);

            if (Objects.isNull(productById)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("class-name", "integrating APIS");

            ProductResponseDTO dto = ProductMapper.getProductResponseDTOFromProduct(productById);

            return new ResponseEntity<>(dto, headers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets the all products.
     *
     * @return the all products
     */
    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {

        List<Product> products = productService.getAllProducts();

        List<ProductResponseDTO> productResponseDTOList = ProductMapper
                .getProductResponseDTOListFromProducts(products);

        return new ResponseEntity<>(productResponseDTOList, HttpStatus.OK);
    }

    /**
     * Patch product.
     *
     * @param productId         the product id
     * @param productRequestDTO the dto
     * @return the http entity
     */
    @PatchMapping("/{productId}")
    public HttpEntity<ProductResponseDTO> patchProduct(@PathVariable("productId") Long productId,
                                                       @RequestBody ProductRequestDTO productRequestDTO) throws Exception {

        try {
            Product product = productService.patchProduct(productId,
                    ProductMapper.getProductFromProductRequestDTO(productRequestDTO));
            ProductResponseDTO productResponseDTOFromProduct = ProductMapper.getProductResponseDTOFromProduct(product);

            return new ResponseEntity<>(productResponseDTOFromProduct, HttpStatus.OK);

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    /**
     * Creates the product.
     *
     * @param dto the dto
     * @return the string
     */
    @PostMapping("/")
    public String createProduct(@RequestBody ProductRequestDTO dto) {

        return "product created.. + " + dto.getTitle() + " " + dto.getCategory();
    }

}
