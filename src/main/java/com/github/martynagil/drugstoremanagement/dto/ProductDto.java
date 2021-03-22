package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    private String name;
    private String barcode;
    private BigDecimal price;
    private Long brandId;
    private Long productTypeId;

    public ProductDto(Long id, String name, String barcode, BigDecimal price, Long brandId, Long productTypeId) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.brandId = brandId;
        this.productTypeId = productTypeId;
    }

    @JsonCreator
    public ProductDto(@JsonProperty("name") String name,
                      @JsonProperty("barcode") String barcode,
                      @JsonProperty("price") BigDecimal price,
                      @JsonProperty("brandId") Long brandId,
                      @JsonProperty("productTypeId") Long productTypeId) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.brandId = brandId;
        this.productTypeId = productTypeId;
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

    public Long getBrandId() {
        return brandId;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }
}
