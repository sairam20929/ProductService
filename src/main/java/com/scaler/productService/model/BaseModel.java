package com.scaler.productService.model;

import java.util.Date;

import lombok.Data;


@Data
public class BaseModel {
	private Long id;
	private String createBy;
	private Date createdAt;
	private Boolean isDeleted;

}
