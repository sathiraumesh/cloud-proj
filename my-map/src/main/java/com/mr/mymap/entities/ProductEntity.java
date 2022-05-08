package com.mr.mymap.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

import java.math.BigInteger;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String productId;

    BigInteger merchantId;

    private String name;

    private String section;

    private String rack;

    private int number;

    private String price;

    private String imagePath;

    private double lon;

    private double lat;
}
