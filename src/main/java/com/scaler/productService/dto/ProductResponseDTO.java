package com.scaler.productService.dto;

import com.scaler.productService.dto.fakestoreAPI.Rating;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class ProductResponseDTO.
 */
@Data
@NoArgsConstructor
public class ProductResponseDTO {
	
	private String title;
	private Double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;

}
