package com.scaler.productService.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity // product
public class Product extends BaseModel {

    private String title;
    private Double price;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private String image;

}
