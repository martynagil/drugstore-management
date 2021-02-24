package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDto {

    Long id;
    String city;
    String postCode;
    String streetAndNumber;

    public AddressDto(Long id, String city, String postCode, String streetAndNumber) {
        this.id = id;
        this.city = city;
        this.postCode = postCode;
        this.streetAndNumber = streetAndNumber;
    }

    @JsonCreator
    public AddressDto(
            @JsonProperty("city") String city,
            @JsonProperty("postCode") String postCode,
            @JsonProperty("streetAndNumber") String streetAndNumber) {
        this.city = city;
        this.postCode = postCode;
        this.streetAndNumber = streetAndNumber;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }
}
