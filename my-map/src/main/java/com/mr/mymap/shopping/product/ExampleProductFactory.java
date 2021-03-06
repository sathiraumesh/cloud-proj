package com.mr.mymap.shopping.product;

import com.google.api.services.content.model.*;
import com.google.common.collect.ImmutableList;
import com.mr.mymap.shopping.content.samples.ContentConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating Products to be inserted by the ProductInsert and ProductBatchInsert samples.
 */
public class ExampleProductFactory {
    private static final String CHANNEL = "online";
    private static final String CONTENT_LANGUAGE = "en";
    private static final String TARGET_COUNTRY = "GB";
    private static final String OFFER_ID = "test123";
    private static final int PRODUCT_COUNT = 10;

    public static String sampleProductId() {
        return sampleProductId(OFFER_ID);
    }

    public static String sampleProductId(String offerId) {
        return CHANNEL + ":" + CONTENT_LANGUAGE + ":" + TARGET_COUNTRY + ":" + offerId;
    }

    public static Product create(ContentConfig config) {
        return create(config, OFFER_ID);
    }

    public static Product create(ContentConfig config, String offerId) {
        String websiteUrl = config.getWebsiteUrl();

        if (websiteUrl == null || websiteUrl.equals("")) {
            throw new IllegalStateException(
                    "Cannot create example products without a configured website");
        }

        return new Product()
                .setOfferId(offerId)
                .setTitle("Harry Potter and the Philosopher's Stone")
                .setDescription("Magical world")
                .setLink(websiteUrl + "/harry-potter-philosophers-stone.html")
                .setImageLink(websiteUrl + "/harry-potter-philosophers-stone.jpg")
                .setChannel(CHANNEL)
                .setContentLanguage(CONTENT_LANGUAGE)
                .setTargetCountry(TARGET_COUNTRY)
                .setAvailability("in stock")
                .setCondition("new")
                .setGoogleProductCategory("Media > Books")
                .setGtin("9780007350897")
                .setPrice(new Price().setValue("3.00").setCurrency("GBP"))
                .setShipping(
                        ImmutableList.of(
                                new ProductShipping()
                                        .setPrice(new Price().setValue("0.99").setCurrency("GBP"))
                                        .setCountry("GB")
                                        .setService("1st class post")));
    }

    public static ProductsCustomBatchRequest createBatch(ContentConfig config, String prefix) {
        List<ProductsCustomBatchRequestEntry> productsBatchRequestEntries = new ArrayList<>();
        for (int i = 0; i < PRODUCT_COUNT; i++) {
            productsBatchRequestEntries.add(
                    new ProductsCustomBatchRequestEntry()
                            .setBatchId((long) i)
                            .setMerchantId(config.getMerchantId())
                            .setProduct(create(config, prefix + i))
                            .setMethod("insert"));
        }
        return new ProductsCustomBatchRequest().setEntries(productsBatchRequestEntries);
    }
}
