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

    @Deprecated
    protected WorkTime() {
    }

    public WorkTime(LocalDateTime startDate) {
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
}
