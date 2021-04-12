package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DeliveryEntryId implements Serializable {

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	private Delivery delivery;

	@Deprecated
	protected DeliveryEntryId() {
	}

	public DeliveryEntryId(Product product, Delivery delivery) {
		this.product = product;
		this.delivery = delivery;
	}

	public Product getProduct() {
		return product;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DeliveryEntryId that = (DeliveryEntryId) o;
		return product.equals(that.product) &&
				delivery.equals(that.delivery);
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, delivery);
	}
}
