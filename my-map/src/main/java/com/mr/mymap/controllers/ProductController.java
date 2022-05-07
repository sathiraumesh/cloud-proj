package com.mr.mymap.controllers;

import com.mr.mymap.domain.Product;
import com.mr.mymap.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/products")
@AllArgsConstructor
public class ProductController {
    private final  ProductService productService;

    @GetMapping("/list")
    public List<Product> getProducts(@RequestParam String name) {
        return productService.getProductsByName(name);
    }

    @GetMapping("{merchantId}")
    public List<Product> getProductsbyMerchantId(@PathVariable String merchantId) {
        return productService.getProductsByMerchantId(merchantId);
    }

    @PatchMapping("{id}")
    public Product patchProduct(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
        return productService.patchProduct(id, fields);
    }

    @GetMapping()
    public String getProduct() {
        return "The product list";
    }
}
