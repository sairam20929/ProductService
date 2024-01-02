package com.scaler.productService.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.productService.dto.CreateProductRequestDTO;
import com.scaler.productService.dto.fakestoreAPI.FakeStoreProductResponse;
import com.scaler.productService.service.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private IProductService productService;

	public ProductController(IProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{productId}")
	public ResponseEntity<FakeStoreProductResponse> getProductById(@PathVariable("productId") Long productId) {

		FakeStoreProductResponse productById = productService.getProductById(productId);

		try {

			if (Objects.isNull(productById)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("class-name", "integrating APIS");

			return new ResponseEntity<>(productById, headers, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<FakeStoreProductResponse>> getAllProducts() {

		List<FakeStoreProductResponse> allProducts = productService.getAllProducts();
		
		return new ResponseEntity<>(allProducts, HttpStatus.OK);
	}

	@PostMapping("/")
	public String createProduct(@RequestBody CreateProductRequestDTO dto) {

		return "product created.. + " + dto.getProductName() + " " + dto.getCategory();
	}

}
