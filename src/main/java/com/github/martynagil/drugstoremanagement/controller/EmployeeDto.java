package com.github.martynagil.drugstoremanagement.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.model.Shop;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class EmployeeDto {

    private Long id;
    private String name;
    private String surname;
    private String telephoneNumber;
    private LocalDate dateOfEmployment;
    private LocalDate dateOfDismissal;
    private String email;
    private Shop shop;

    public EmployeeDto(Long id, String name, String surname, String telephoneNumber, LocalDate dateOfEmployment, LocalDate dateOfDismissal, String email, Shop shop) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.dateOfEmployment = dateOfEmployment;
        this.dateOfDismissal = dateOfDismissal;
        this.email = email;
        this.shop = shop;
    }

    @JsonCreator
    public EmployeeDto(
            @JsonProperty String name,
            @JsonProperty String surname,
            @JsonProperty String telephoneNumber,
            @JsonProperty LocalDate dateOfEmployment,
            @JsonProperty String email,
            @JsonProperty Shop shop,
            @JsonProperty LocalDate dateOfDismissal) {
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.dateOfEmployment = dateOfEmployment;
        this.email = email;
        this.shop = shop;
        this.dateOfDismissal = dateOfDismissal;
    }

    public static EmployeeDto from(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getSurname(),
                employee.getTelephoneNumber(),
                employee.getDateOfEmployment(),
                employee.getDateOfDismissal(),
                employee.getEmail(),
                employee.getShop()
                );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public LocalDate getDateOfDismissal() {
        return dateOfDismissal;
    }

    public String getEmail() {
        return email;
    }

    public Shop getShop() {
        return shop;
    }

    public void setDateOfDismissal(LocalDate dateOfDismissal) {
        this.dateOfDismissal = dateOfDismissal;
    }
}
