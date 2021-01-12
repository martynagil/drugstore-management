package com.github.martynagil.drugstoremanagement.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.martynagil.drugstoremanagement.model.Salary;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

public class SalaryDto {

    private Long id;
    private YearMonth month;
    private BigDecimal amount;

    public SalaryDto(Long id, YearMonth month, BigDecimal amount) {
        this.id = id;
        this.month = month;
        this.amount = amount;
    }

    @JsonCreator
    public SalaryDto(
            @JsonProperty("month") YearMonth month,
            @JsonProperty("amount") BigDecimal amount) {
        this.month = month;
        this.amount = amount;
    }

    public static SalaryDto from(Salary salary) {
        return new SalaryDto(
                salary.getId(),
                salary.getMonth(),
                salary.getAmount()
        );
    }

    public Long getId() {
        return id;
    }

    public YearMonth getMonth() {
        return month;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
