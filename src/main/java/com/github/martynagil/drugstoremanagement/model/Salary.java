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
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private BigDecimal amount;

    @Deprecated
    protected Salary() {
    }

    public Salary(YearMonth month, BigDecimal amount) {
        this.year = month.getYear();
        this.month = month.getMonthValue();
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public YearMonth getMonth() {
        return YearMonth.of(year, month);
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
