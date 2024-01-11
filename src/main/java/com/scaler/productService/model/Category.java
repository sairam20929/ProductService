package com.scaler.productService.model;

import java.util.List;

import lombok.Data;

@Data
public class Category extends BaseModel {

    private String name;
    private String description;
    private List<Product> products;
}
