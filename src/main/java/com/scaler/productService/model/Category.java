package com.scaler.productService.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Category extends BaseModel {

    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
