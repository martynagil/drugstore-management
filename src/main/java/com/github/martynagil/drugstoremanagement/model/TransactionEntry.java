package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
// TODO: 28.12.2020
//@Entity
@Table(name = "transaction_entries")
public class TransactionEntry {

    @Column
    private int count;

    @ManyToOne
    private Product product;



}
