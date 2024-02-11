package com.scaler.productService.service;

import com.scaler.productService.model.Product;
import com.scaler.productService.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("selfProductService")
public class SelfProductService implements IProductService {

    private final ProductRepository repo;

    public SelfProductService(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product getProductById(Long productId) throws Exception {

        if (repo.findById(productId).isEmpty()) {
            throw new Exception("Product does not exist");
        }

        return repo.findById(productId).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Product patchProduct(Long productId, Product product) throws Exception {

        Product existingProduct = getProductById(productId);

        if (Objects.isNull(existingProduct)) {
            throw new Exception("Product does not exist");

        }else{
            if (Objects.nonNull(product.getTitle())) {
                existingProduct.setTitle(product.getTitle());
            }
            if (Objects.nonNull(product.getPrice())) {
                existingProduct.setPrice(product.getPrice());
            }
            if (Objects.nonNull(product.getDescription())) {
                existingProduct.setDescription(product.getDescription());
            }
            if (Objects.nonNull(product.getImage())) {
                existingProduct.setImage(product.getImage());
            }
        }

        return repo.save(existingProduct);
    }

    @Override
    public Product postProduct(Product product) {

        return repo.save(product);
    }

    @Override
    public void deleteProduct(Long productId) throws Exception {

        if (!repo.existsById(productId)) {
            throw new Exception("Product does not exist");
        }
        repo.deleteById(productId);
    }
}
