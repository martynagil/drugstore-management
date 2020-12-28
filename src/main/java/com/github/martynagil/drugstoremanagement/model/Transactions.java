package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime dateTime;

    @ManyToOne(optional = false)
    private Shop shop;

    @Deprecated
    protected Transactions() {
    }

    public Transactions(Shop shop) {
        this.shop = shop;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Shop getShop() {
        return shop;
    }
}
