package com.scaler.productService.dto.fakestoreAPI;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FakeStoreProductRequest {

	String title;
	Double price;
	String description;
	String category;
	String image;

}
