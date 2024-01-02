package com.scaler.productService.service;

import java.util.List;
import com.scaler.productService.dto.fakestoreAPI.FakeStoreProductResponse;

public interface IProductService {
	
	FakeStoreProductResponse getProductById(Long productId);

    List<FakeStoreProductResponse> getAllProducts();

}
