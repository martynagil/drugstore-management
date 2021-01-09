package com.github.martynagil.drugstoremanagement.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;

public class SalaryDto {

    Long id;
    Year year;
    Month month;
    BigDecimal amount;

    public SalaryDto(Long id, Year year, Month month, BigDecimal amount) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.amount = amount;
    }

    @JsonCreator
    public SalaryDto(
            @JsonProperty("year") Year year,
            @JsonProperty("month") Month month,
            @JsonProperty("amount") BigDecimal amount) {
        this.year = year;
        this.month = month;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Year getYear() {
        return year;
    }

    public Month getMonth() {
        return month;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
