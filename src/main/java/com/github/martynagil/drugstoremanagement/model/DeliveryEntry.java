package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "delivery_entries")
public class DeliveryEntry {

	@EmbeddedId
	private DeliveryEntryId deliveryEntryId;

	@Column(nullable = false)
	private int count;

	@Deprecated
	protected DeliveryEntry() {
	}

	public DeliveryEntry(DeliveryEntryId deliveryEntryId, int count) {
		this.deliveryEntryId = deliveryEntryId;
		this.count = count;
	}

	public DeliveryEntryId getDeliveryEntryId() {
		return deliveryEntryId;
	}

	public int getCount() {
		return count;
	}
}
