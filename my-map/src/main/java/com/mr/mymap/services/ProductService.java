package com.mr.mymap.services;


import com.mr.mymap.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> getProductsByName(String name);

    List<Product> getProductsByMerchantId(String merchantId);

    Product patchProduct(Long id, Map<Object, Object> fields);
}
