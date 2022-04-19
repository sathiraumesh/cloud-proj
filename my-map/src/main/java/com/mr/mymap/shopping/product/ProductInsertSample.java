package com.mr.mymap.shopping.product;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.content.model.Product;
import com.mr.mymap.shopping.content.samples.ContentSample;

import java.io.IOException;


/** Sample that inserts a product. The product created here is used in other samples. */
public class ProductInsertSample extends ContentSample {
    public ProductInsertSample(String[] args) throws IOException {
        super(args);
    }

    @Override
    public void execute() throws IOException {
        checkNonMCA();

        // Create a product with the defaults defined within ExampleProductFactory.
        Product product = ExampleProductFactory.create(config);

        try {
            Product result = content.products().insert(this.config.getMerchantId(), product).execute();
        } catch (GoogleJsonResponseException e) {
            checkGoogleJsonResponseException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        new ProductInsertSample(args).execute();
    }
}