package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String telephoneNumber;

    @Column(nullable = false)
    private LocalDate dateOfEmployment;

    @Column(nullable = false)
    private LocalDate dateOfDismissal;

    @Column(nullable = false)
    private String email;

    @ManyToOne(optional = false)
    private Shop shop;

    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<Salary> salaries = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<WorkTime> workTimes = new ArrayList<>();

    @Deprecated
    protected Employee() {
    }

    public Employee(String name, String surname, String telephoneNumber, LocalDate dateOfEmployment, LocalDate dateOfDismissal, String email, Shop shop) {
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.dateOfEmployment = dateOfEmployment;
        this.dateOfDismissal = dateOfDismissal;
        this.email = email;
        this.shop = shop;
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
}
