package com.scaler.productService.mapper;

import java.util.ArrayList;
import java.util.List;

import com.scaler.productService.dto.ProductRequestDTO;
import com.scaler.productService.dto.ProductResponseDTO;
import com.scaler.productService.dto.fakestoreAPI.FakeStoreProductResponse;
import com.scaler.productService.model.Product;

/**
 * The Class ProductMapper.
 */
public class ProductMapper {

	/**
	 * Gets the product from fake store product.
	 *
	 * @param fakeStoreProductResponse the fake store product response
	 * @return the product from fake store product
	 */
	public static Product getProductFromFakeStoreProduct(FakeStoreProductResponse fakeStoreProductResponse) {

		Product product = new Product();
		product.setTitle(fakeStoreProductResponse.getTitle());
		product.setPrice(fakeStoreProductResponse.getPrice());
		product.setDescription(fakeStoreProductResponse.getDescription());
		product.setCategory(fakeStoreProductResponse.getCategory());
		product.setImage(fakeStoreProductResponse.getImage());

		return product;
	}

	/**
	 * Gets the product list from fake store list.
	 *
	 * @param fakeStoreProductResponses the fake store product responses
	 * @return the product list from fake store list
	 */
	public static List<Product> getProductListFromFakeStoreList(FakeStoreProductResponse[] fakeStoreProductResponses) {

		List<Product> products = new ArrayList<>();
		for (FakeStoreProductResponse fakeStoreProductResponse : fakeStoreProductResponses) {
			products.add(getProductFromFakeStoreProduct(fakeStoreProductResponse));
		}

		return products;
	}

	/**
	 * Gets the product from product request DTO.
	 *
	 * @param productRequestDTO the product request DTO
	 * @return the product from product request DTO
	 */
	public static Product getProductFromProductRequestDTO(ProductRequestDTO productRequestDTO) {

		Product product = new Product();
		product.setTitle(productRequestDTO.getTitle());
		product.setPrice(productRequestDTO.getPrice());
		product.setDescription(productRequestDTO.getDescription());
		product.setCategory(productRequestDTO.getCategory());
		product.setImage(productRequestDTO.getImage());

		return product;
	}

	/**
	 * Gets the product response DTO from product.
	 *
	 * @param product the product
	 * @return the product response DTO from product
	 */
	public static ProductResponseDTO getProductResponseDTOFromProduct(Product product) {

		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setTitle(product.getTitle());
		productResponseDTO.setPrice(product.getPrice());
		productResponseDTO.setDescription(product.getDescription());
		productResponseDTO.setCategory(product.getCategory());
		productResponseDTO.setImage(product.getImage());

		return productResponseDTO;
	}

	/**
	 * Gets the product response DTO list from products.
	 *
	 * @param products the products
	 * @return the product response DTO list from products
	 */
	public static List<ProductResponseDTO> getProductResponseDTOListFromProducts(List<Product> products) {

		List<ProductResponseDTO> responseDTOS = new ArrayList<>();
		for (Product product : products) {
			responseDTOS.add(getProductResponseDTOFromProduct(product));
		}

		return responseDTOS;
	}

}
