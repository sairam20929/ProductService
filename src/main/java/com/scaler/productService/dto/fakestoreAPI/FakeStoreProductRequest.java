package com.scaler.productService.dto.fakestoreAPI;

import com.scaler.productService.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FakeStoreProductRequest {

	private String title;
	private Double price;
	private String description;
	private Category category;
	private String image;
	private Rating rating;
}
