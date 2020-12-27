package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

@Entity
@Table(name = "salaries")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Year year;

    @Column(nullable = false)
    private Month month;

    @Column(nullable = false)
    private BigDecimal amount;

    // TODO: 27.12.2020 na pewno będziemy się chcieli dostać do wypłat pracownika w aplikacji, więc fajnie jakby Employee miał listę swoich wypłat (bidirectional binding)
    @ManyToOne(optional = false)
    private Employee employee;

    @Deprecated
    protected Salary() {
    }

    public Salary(Year year, Month month, BigDecimal amount, Employee employee) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.employee = employee;
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

    public Employee getEmployee() {
        return employee;
    }
}
