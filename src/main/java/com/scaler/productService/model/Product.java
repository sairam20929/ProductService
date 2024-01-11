package com.scaler.productService.model;

import com.scaler.productService.dto.fakestoreAPI.Rating;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Product extends BaseModel {

	private String title;
	private Double price;
	private String description;
	@ManyToOne(cascade = CascadeType.ALL) // NextClass
	private Category category;
	private String image;

}
