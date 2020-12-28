package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "products_types")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Deprecated
    protected ProductType() {
    }

    public ProductType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
