package com.mr.mymap.services;


import com.mr.mymap.domain.Product;
import com.mr.mymap.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.mr.mymap.repositories.ProductRepository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceBean implements ProductService {

    private final ProductRepository productRepository;

    private ModelMapper modelMapper = new ModelMapper();

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
    public List<Product> getProductsByMerchantId(String merchantId) {
        return productRepository.findProdudctsBymerchantId(new BigInteger(merchantId))
                .stream().map(p -> {
                    Product product = new Product();
                    product.setId(p.getId());
                    product.setProductId(p.getProductId());
                    product.setName(p.getName());
                    product.setImagePath(p.getImagePath());
                    product.setLat(p.getLat());
                    product.setLon(p.getLat());
                    product.setRack(p.getRack());
                    product.setSection(p.getSection());
                    return product;
                }).collect(Collectors.toList());
    }

    @Override
    public Product patchProduct(long id, Map<Object, Object> fields) {
        Optional<ProductEntity> product = productRepository.findProductByProductId(id);
        if (product.isPresent()){
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(ProductEntity.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, product.get(), value);
            });
            return modelMapper.map(productRepository.save(product.get()), Product.class);
        }
        return new Product();
    }

}
