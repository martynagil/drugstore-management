package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shops")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
	private Address address;

	@OneToMany(mappedBy = "stockEntryId.shop")
	private List<StockEntry> stockEntries = new ArrayList<>();

	@Deprecated
	protected Shop() {
	}

	public Shop(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public List<StockEntry> getStockEntries() {
		return stockEntries;
	}
}
