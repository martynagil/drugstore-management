package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate submissionDate;

    @Column(nullable = false)
    private String reason;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ComplaintStatus complaintStatus;

    @OneToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private Transaction transaction;

    @Deprecated
    protected Complaint() {
    }

    public Complaint(LocalDate submissionDate, String reason, Product product, Transaction transaction) {
        this.submissionDate = submissionDate;
        this.reason = reason;
        complaintStatus = ComplaintStatus.SUBMITTED;
        this.product = product;
        this.transaction = transaction;
    }

    public void updateStatus(ComplaintStatus complaintStatus) {
        if (this.complaintStatus == ComplaintStatus.SUBMITTED
                && complaintStatus != ComplaintStatus.SUBMITTED) {
            this.complaintStatus = complaintStatus;
        }
    }

    public Long getId() {
        return id;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
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
