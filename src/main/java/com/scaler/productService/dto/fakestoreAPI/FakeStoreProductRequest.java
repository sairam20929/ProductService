package com.scaler.productService.dto.fakestoreAPI;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FakeStoreProductRequest {

	private String title;
	private Double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
}
