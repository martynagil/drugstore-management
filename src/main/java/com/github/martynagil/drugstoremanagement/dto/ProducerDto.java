package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProducerDto {

    private Long id;
    private String name;
    private String email;
    private String telephone;

    public ProducerDto(Long id, String name, String email, String telephone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }

    @JsonCreator
    public ProducerDto(
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("telephone") String telephone) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
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
}
