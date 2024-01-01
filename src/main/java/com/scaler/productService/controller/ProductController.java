package com.scaler.productService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.productService.dto.CreateProductRequestDTO;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@GetMapping("/")
	public String getAllProducts() {
		return "All products";
	}
	
	@PostMapping("/")
    public String createProduct(@RequestBody CreateProductRequestDTO dto){

        return "product created.. + "+ dto.getProductName() +" "+ dto.getCategory();
    }

    @GetMapping("/{productId}")
    public String getProductById(@PathVariable("productId") Integer productId){
        return "user is---"+productId;
    }

}
