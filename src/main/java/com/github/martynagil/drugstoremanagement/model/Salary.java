package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;

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

    @Deprecated
    protected Salary() {
    }

    public Salary(Year year, Month month, BigDecimal amount) {
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
