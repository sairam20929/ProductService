package com.scaler.productService.model;

import lombok.Data;

@Data
public class Product extends BaseModel {

	private String title;
	private Double price;
	private String description;
	private Category category;
	private String image;

}
