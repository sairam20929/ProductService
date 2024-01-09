package com.scaler.productService.model;

import com.scaler.productService.dto.fakestoreAPI.Rating;

import lombok.Data;

@Data
public class Product extends BaseModel {

	private String title;
	private Double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;

}
