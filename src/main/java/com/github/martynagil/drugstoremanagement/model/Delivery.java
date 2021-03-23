package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "deliveries")
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime time;

	@ManyToOne(optional = false)
	private Shop shop;

	@OneToMany(mappedBy = "deliveryEntryId.delivery")
	private List<DeliveryEntry> deliveryEntries = new ArrayList<>();

	@Deprecated
	protected Delivery() {
	}

	public Delivery(LocalDateTime time, Shop shop, List<DeliveryEntry> deliveryEntries) {
		this.time = time;
		this.shop = shop;
		this.deliveryEntries = deliveryEntries;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public Shop getShop() {
		return shop;
	}

	public List<DeliveryEntry> getDeliveryEntries() {
		return deliveryEntries;
	}
}
