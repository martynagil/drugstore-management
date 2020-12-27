package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;

// TODO: 27.12.2020 tak jak Ci wcześniej mówiłem, generalnie można tak zrobić, ale w springu raczej trzyma się takie rzeczy pod postacią stringa w bazie
// w aplikacji powinny być zwykłym enumem (no chyba, że chcemy żeby te typy produktów były dynamiczne - wtedy faktycznie trzeba zostawic tak jak jest)
// dynamicznie czyli, że pracownik może dodać nowy typ produktu
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
