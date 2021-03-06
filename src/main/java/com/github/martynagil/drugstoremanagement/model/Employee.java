package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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

    @Column(nullable = false, unique = true)
    private String telephoneNumber;

    @Column(nullable = false)
    private LocalDate dateOfEmployment;

    private LocalDate dateOfDismissal;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(optional = false)
    private Shop shop;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id", nullable = false)
    private List<Salary> salaries = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id", nullable = false)
    private List<WorkTime> workTimes = new ArrayList<>();

    @Deprecated
    protected Employee() {
    }

    public Employee(String name, String surname, String telephoneNumber, LocalDate dateOfEmployment, String email, Shop shop) {
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.dateOfEmployment = dateOfEmployment;
        this.email = email;
        this.shop = shop;
    }

    public void startWork() {
        workTimes.add(new WorkTime(LocalDateTime.now()));
    }

    public void endWork() {
        workTimes.stream()
                .max(Comparator.comparing(workTime -> workTime.getStartDate()))
                .orElseThrow(() -> new EntityNotFoundException())
                .endWork(LocalDateTime.now());
    }

    public void addSalary(Salary salary) {
        salaries.add(salary);
    }

    public void dismiss(LocalDate dateOfDismissal) {
        if (!isDismissed()) {
            this.dateOfDismissal = dateOfDismissal;
        }
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

    public List<Salary> getSalaries() {
        return salaries;
    }

    public List<WorkTime> getWorkTimes() {
        return workTimes;
    }

    private boolean isDismissed() {
        return dateOfDismissal != null;
    }

}
