package com.scaler.productService.service;

import java.util.List;

import com.scaler.productService.model.Product;

public interface IProductService {
	
	Product getProductById(Long productId);

    List<Product> getAllProducts();

	Product patchProduct(Long productId, Product dto) throws Exception;

}
