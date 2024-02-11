package com.scaler.productService.service;

import java.util.List;

import com.scaler.productService.model.Product;

public interface IProductService {
	
	Product getProductById(Long productId) throws Exception;

    List<Product> getAllProducts();

	Product patchProduct(Long productId, Product dto) throws Exception;

	Product postProduct(Product product) throws Exception;

	void deleteProduct(Long productId) throws Exception;
}
