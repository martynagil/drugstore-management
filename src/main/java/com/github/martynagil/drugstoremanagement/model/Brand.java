package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @ManyToOne(optional = false)
    private Producer producer;

    @Deprecated
    protected Brand() {
    }

    public Brand(String name, String email, String telephone, Producer producer) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.producer = producer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public Producer getProducer() {
        return producer;
    }
}
