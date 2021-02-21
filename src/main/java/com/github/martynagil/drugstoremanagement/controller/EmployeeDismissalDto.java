package com.github.martynagil.drugstoremanagement.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class EmployeeDismissalDto {

    private LocalDate dismissalDate;

    @JsonCreator
    public EmployeeDismissalDto(@JsonProperty("dismissalDate") LocalDate dismissalDate) {
        this.dismissalDate = dismissalDate;
    }

    public LocalDate getDismissalDate() {
        return dismissalDate;
    }
}
