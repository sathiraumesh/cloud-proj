package com.mr.mymap.services;


import com.mr.mymap.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductsByName(String name);
}
