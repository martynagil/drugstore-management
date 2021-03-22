package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BrandDto {

   private Long id;
   private String name;
   private String email;
   private String telephone;
   private Long producerId;

    public BrandDto(Long id, String name, String email, String telephone, Long producerId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.producerId = producerId;
    }

    @JsonCreator
    public BrandDto(
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("telephone") String telephone,
            @JsonProperty("producerId") Long producerId) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.producerId = producerId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public Long getProducerId() {
        return producerId;
    }
}
