package com.mr.mymap.services;


import com.mr.mymap.domain.Product;
import com.mr.mymap.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mr.mymap.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service()
public class ProductServiceBean implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findProductsByName(name).stream()
                .map(p -> {
                    Product product = new Product();
                    product.setId(p.getId());
                    product.setName(p.getName());
                    product.setImagePath(p.getImagePath());
                    return product;
                }).collect(Collectors.toList());
    }
    @Override
    public void updateProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setProductId(product.getProductId());
        productEntity.setName(product.getName());
        productEntity.setImagePath(product.getImagePath());
        productEntity.setLat(product.getLat());
        productEntity.setLon(product.getLon());
        productRepository.save(productEntity);
    }
}
