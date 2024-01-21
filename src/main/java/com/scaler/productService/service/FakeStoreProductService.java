package com.scaler.productService.service;

import java.util.List;
import java.util.Objects;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.scaler.productService.dto.fakestoreAPI.FakeStoreProductRequest;
import com.scaler.productService.dto.fakestoreAPI.FakeStoreProductResponse;
import com.scaler.productService.mapper.ProductMapper;
import com.scaler.productService.model.Product;
import com.scaler.productService.utility.HttpUtil;

/**
 * The type Product service.
 */
@Service
public class FakeStoreProductService implements IProductService {

    /**
     * The Rest template.
     */
    private final RestTemplateBuilder restTemplate;

    /**
     * Instantiates a new Product service.
     *
     * @param restTemplate the rest template
     */
    public FakeStoreProductService(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long productId) {

        FakeStoreProductResponse fakeStoreProductResponse = restTemplate.build()
                .getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductResponse.class, productId)
                .getBody();

        assert fakeStoreProductResponse != null;
        return ProductMapper.getProductFromFakeStoreProduct(fakeStoreProductResponse);
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductResponse[] fakeStoreProductResponses = restTemplate.build()
                .getForEntity("https://fakestoreapi.com/products", FakeStoreProductResponse[].class).getBody();

        assert fakeStoreProductResponses != null;
        return ProductMapper.getProductListFromFakeStoreList(fakeStoreProductResponses);
    }

    @Override
    public Product patchProduct(Long productId, Product product) throws Exception {

        Product existingProduct = getProductById(productId);
        if (Objects.isNull(existingProduct)) {
            throw new Exception("Product does not exist");
        }

        FakeStoreProductRequest fakeStoreProductRequest = new FakeStoreProductRequest();
        fakeStoreProductRequest.setTitle(product.getTitle());
        fakeStoreProductRequest.setPrice(product.getPrice());
        fakeStoreProductRequest.setDescription(product.getDescription());
//		fakeStoreProductRequest.setCategory(product.getCategory());
        fakeStoreProductRequest.setImage(product.getImage());

        FakeStoreProductResponse fakeStoreProductResponse = HttpUtil
                .requestForEntity(restTemplate, HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}",
                        fakeStoreProductRequest, FakeStoreProductResponse.class, productId)
                .getBody();

        assert fakeStoreProductResponse != null;
        return ProductMapper.getProductFromFakeStoreProduct(fakeStoreProductResponse);

    }

    @Override
    public Product postProduct(Product product) {
//		need to implement...
        return null;
    }

}
