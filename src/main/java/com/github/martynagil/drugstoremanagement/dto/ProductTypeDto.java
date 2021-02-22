package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductTypeDto {

    private Long id;
    private String name;

    public ProductTypeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonCreator
    public ProductTypeDto(@JsonProperty("name") String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
