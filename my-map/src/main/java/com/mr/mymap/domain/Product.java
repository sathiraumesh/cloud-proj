package com.mr.mymap.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Product {

    private Long id;

    private String productId;

    BigInteger merchantId;

    private String name;

    private String section;

    private String rack;

    private int number;

    private String price;

    private String imagePath;

    private Double lon;

    private Double lat;
}
