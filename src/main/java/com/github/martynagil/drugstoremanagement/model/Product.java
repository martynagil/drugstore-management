package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String barcode;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(optional = false)
    private Brand brand;

    @ManyToOne(optional = false)
    private ProductType productType;

    @Deprecated
    protected Product() {
    }

    public Product(String name, String barcode, BigDecimal price, Brand brand, ProductType productType) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.brand = brand;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Brand getBrand() {
        return brand;
    }

    public ProductType getProductType() {
        return productType;
    }
}
