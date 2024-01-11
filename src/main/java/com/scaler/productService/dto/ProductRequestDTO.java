package com.scaler.productService.dto;

import com.scaler.productService.dto.fakestoreAPI.Rating;

import com.scaler.productService.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class ProductRequestDTO.
 */
@Data
@NoArgsConstructor
public class ProductRequestDTO {
	
	private String title;
	private Double price;
	private String description;
	private Category category;
	private String image;
	private Rating rating;
}
