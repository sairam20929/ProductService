package com.scaler.productService.controller;

import com.scaler.productService.dto.ProductResponseDTO;
import com.scaler.productService.model.Product;
import com.scaler.productService.service.SelfProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Mock
    private SelfProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void getProductById() throws Exception {

//        Arrange
        Product product = new Product();
        product.setTitle("Product 1");
        product.setPrice(100.0);
        product.setDescription("Product 1 description");
        product.setImage("product1.jpg");

        when(productService.getProductById(1L)).thenReturn(product);

//        Act
        ResponseEntity<ProductResponseDTO> byId = productController.getProductById(1L);

//        Assert
        assertNotNull(byId);
        assertEquals("Product 1", Objects.requireNonNull(byId.getBody()).getTitle());

    }

    @Test
    void getProductByIdThrowsException() throws Exception {

//        Arrange
        when(productService.getProductById(1L)).thenThrow(new Exception("Test exception"));

//        Act
        ResponseEntity<ProductResponseDTO> response = productController.getProductById(1L);

//        Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void patchProduct() {
    }

    @Test
    void createProduct() {
    }
}