package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "work_times")
public class WorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    // TODO: 27.12.2020 ten sam przypadek co w Salary
    @ManyToOne
    private Employee employee;

    @Deprecated
    protected WorkTime() {
    }

    // TODO: 27.12.2020 czy taki konstruktor ma sens? jak będziemy tworzyć wpis (przy wejściu pracownika) to nie będziemy znać czasu wyjścia
    public WorkTime(Employee employee, LocalDateTime startDate) {
        this.employee = employee;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Employee getEmployee() {
        return employee;
    }
}
