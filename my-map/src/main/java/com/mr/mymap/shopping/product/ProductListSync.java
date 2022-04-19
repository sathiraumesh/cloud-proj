package com.mr.mymap.shopping.product;


import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.content.ShoppingContent;
import com.google.api.services.content.model.Product;
import com.google.api.services.content.model.ProductsListResponse;
import com.mr.mymap.entities.ProductEntity;
import com.mr.mymap.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mr.mymap.shopping.content.samples.ContentSample;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

@Component
public class ProductListSync extends ContentSample {

    @Autowired
    private ProductRepository productRepository;

    public ProductListSync(String[] args) throws IOException {
        super(args);
    }

    public void listProductsForMerchant(BigInteger merchantId, ShoppingContent content)
            throws IOException {
        ShoppingContent.Products.List productsList = content.products().list(merchantId);

        ProductsListResponse page = null;

        do {
            if (page != null) {
                productsList.setPageToken(page.getNextPageToken());
            }
            page = productsList.execute();
            if (page.getResources() == null) {
                System.out.println("No products found.");
                return;
            }
            for (Product product : page.getResources()) {

               Optional<ProductEntity> productEntity =  productRepository
                       .findProductByMerchantProductId(product.getId());

                if (productEntity.isEmpty()){
                    ProductEntity newProduct = new ProductEntity();
                    newProduct.setProductId(product.getId());
                    newProduct.setName(product.getTitle());
                    newProduct.setImagePath(product.getImageLink());
                    productRepository.save(newProduct);
                }
            }
        } while (page.getNextPageToken() != null);
    }

    @Override
    public void execute() throws IOException {
        checkNonMCA();

        try {
            listProductsForMerchant(config.getMerchantId(), content);
        } catch (GoogleJsonResponseException e) {
            checkGoogleJsonResponseException(e);
        }
    }
}
