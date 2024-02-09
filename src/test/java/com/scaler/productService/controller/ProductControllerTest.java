package com.scaler.productService.controller;

import com.scaler.productService.dto.ProductRequestDTO;
import com.scaler.productService.dto.ProductResponseDTO;
import com.scaler.productService.model.Product;
import com.scaler.productService.service.SelfProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
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
        assertEquals(HttpStatus.OK, byId.getStatusCode());

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

//        Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());

        when(productService.getAllProducts()).thenReturn(products);

//        Act
        ResponseEntity<List<ProductResponseDTO>> allProducts = productController.getAllProducts();

//        Assert
        assertNotNull(allProducts);
        assertEquals(3, Objects.requireNonNull(allProducts.getBody()).size());
        assertEquals(HttpStatus.OK, allProducts.getStatusCode());
    }

    @Test
    void patchProduct() throws Exception {

//        Arrange
        Product product = new Product();
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();

        when(productService.patchProduct(1L, new Product())).thenReturn(product);

//        Act
        ResponseEntity<ProductResponseDTO> response = productController.patchProduct(1L, productRequestDTO);

//        Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void patchProductThrowsException() throws Exception {

//        Arrange
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();

        when(productService.patchProduct(1L, new Product())).thenThrow(new Exception("Test exception"));

//        Act and Assert
        assertThrows(Exception.class, () -> productController.patchProduct(1L, productRequestDTO));

    }

    @Test
    void createProduct() throws Exception {

//        Arrange
        Product product = new Product();
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();

        when(productService.postProduct(product)).thenReturn(product);

//        Act
        ResponseEntity<ProductResponseDTO> response = productController.createProduct(productRequestDTO);

//        Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}