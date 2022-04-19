package com.mr.mymap.controllers;

import com.mr.mymap.domain.Product;
import com.mr.mymap.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
@AllArgsConstructor
public class ProductController {
    private final  ProductService productService;

    @GetMapping("/list")
    public List<Product> getProducts(@RequestParam String name) {
        return productService.getProductsByName(name);
    }

    @GetMapping()
    public String getProduct() {
        return "The product list";
    }
}
