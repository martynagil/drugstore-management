package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private ComplaintStatus complaintStatus;

    @OneToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private Transaction transaction;

    @Deprecated
    protected Complaint() {
    }

    public Complaint(LocalDate date, String reason, ComplaintStatus complaintStatus, Product product, Transaction transaction) {
        this.date = date;
        this.reason = reason;
        this.complaintStatus = complaintStatus;
        this.product = product;
        this.transaction = transaction;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    public ComplaintStatus getComplaintStatus() {
        return complaintStatus;
    }

    public Product getProduct() {
        return product;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
