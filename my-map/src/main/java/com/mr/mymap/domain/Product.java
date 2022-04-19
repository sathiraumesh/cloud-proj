package com.mr.mymap.domain;

import lombok.Data;

@Data
public class Product {

    private Long id;

    private String productId;

    private String name;

    private String section;

    private String rack;

    private int number;

    private String price;

    private String imagePath;

    private String lon;

    private String lat;
}
