package com.scaler.productService.controller;

import com.scaler.productService.dto.ProductRequestDTO;
import com.scaler.productService.dto.ProductResponseDTO;
import com.scaler.productService.mapper.ProductMapper;
import com.scaler.productService.model.Product;
import com.scaler.productService.service.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<ProductResponseDTO> patchProduct(@PathVariable("productId") Long productId,
                                                           @RequestBody ProductRequestDTO productRequestDTO) throws Exception {

        try {
            Product product = productService.patchProduct(productId,
                    ProductMapper.getProductFromProductRequestDTO(productRequestDTO));
            ProductResponseDTO productResponseDTO = ProductMapper.getProductResponseDTOFromProduct(product);

            return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    /**
     * Creates the product.
     *
     * @param productRequestDTO the dto
     * @return the string
     */
    @PostMapping("/")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) throws Exception {

        try {
            Product product =
                    productService.postProduct(ProductMapper.getProductFromProductRequestDTO(productRequestDTO));
            ProductResponseDTO productResponseDTO = ProductMapper.getProductResponseDTOFromProduct(product);

            return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
