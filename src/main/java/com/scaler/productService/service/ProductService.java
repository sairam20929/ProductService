package com.scaler.productService.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.scaler.productService.dto.fakestoreAPI.FakeStoreProductResponse;

@Service
public class ProductService implements IProductService {

	RestTemplateBuilder restTemplate;

	public ProductService(RestTemplateBuilder restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public FakeStoreProductResponse getProductById(Long productId) {

		FakeStoreProductResponse forEntity = restTemplate.build()
				.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductResponse.class, productId)
				.getBody();
		return forEntity;
	}

	@Override
	public List<FakeStoreProductResponse> getAllProducts() {

		FakeStoreProductResponse[] dto = restTemplate.build()
				.getForEntity("https://fakestoreapi.com/products", FakeStoreProductResponse[].class).getBody();

		return Arrays.asList(dto);
	}

}
