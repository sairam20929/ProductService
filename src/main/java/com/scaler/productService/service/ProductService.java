package com.scaler.productService.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.scaler.productService.dto.CreateProductRequestDTO;
import com.scaler.productService.dto.fakestoreAPI.FakeStoreProductRequest;
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

	@Override
	public FakeStoreProductResponse patchProduct(Long productId, CreateProductRequestDTO dto) {

		FakeStoreProductRequest requestDTO = new FakeStoreProductRequest();
		requestDTO.setCategory(dto.getCategory());
		requestDTO.setPrice(dto.getPrice());
		requestDTO.setImage(dto.getImageURL());
		requestDTO.setTitle(dto.getProductName());

		ResponseEntity<FakeStoreProductResponse> response = requestForEntity(HttpMethod.PATCH,
				"https://fakestoreapi.com/products/{id}", requestDTO, FakeStoreProductResponse.class, productId);

		return response.getBody();

	}

	private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
			Class<T> responseType, Object... uriVariables) throws RestClientException {

		RestTemplate t = restTemplate.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();

		RequestCallback requestCallback = t.httpEntityCallback(request, responseType);
		ResponseExtractor<ResponseEntity<T>> responseExtractor = t.responseEntityExtractor(responseType);

		return t.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
	}

}
