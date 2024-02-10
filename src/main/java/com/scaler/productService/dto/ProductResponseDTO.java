package com.scaler.productService.dto;

import com.scaler.productService.dto.fakestoreAPI.Rating;

import com.scaler.productService.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class ProductResponseDTO.
 */
@Data
@NoArgsConstructor
public class ProductResponseDTO {

	private Long id;
	private String title;
	private Double price;
	private String description;
	private Category category;
	private String image;
	private Rating rating;

}
