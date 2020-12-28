package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TransactionEntryId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Deprecated
    protected TransactionEntryId() {
    }

    public TransactionEntryId(Product product, Transaction transaction) {
        this.product = product;
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntryId that = (TransactionEntryId) o;
        return product.equals(that.product) &&
                transaction.equals(that.transaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, transaction);
    }
}
