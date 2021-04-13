package com.github.martynagil.drugstoremanagement;

import com.github.martynagil.drugstoremanagement.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EntityFactory {

	public Address newAddress() {
		return new Address(
				"wroclaw",
				"56-988",
				"krzywoustego 110"
		);
	}

	public Brand newBrand() {
		return new Brand(
				"brand name",
				"sdf@sdf.com",
				"23456345",
				newProducer()
		);
	}

	public Complaint newComplaint() {
		return new Complaint(
				LocalDate.of(2021, 12, 30),
				"very serious reason",
				newProduct(),
				newTransaction()
		);
	}

	public Delivery newDelivery(List<DeliveryEntry> deliveryEntries) {
		return new Delivery(
				LocalDateTime.of(2021, 3, 4, 5, 4),
				newShop(),
				deliveryEntries
		);
	}

	public Producer newProducer() {
		return new Producer(
				"producer name",
				"email@producer.com",
				"23456543"
		);
	}

	public Product newProduct() {
		return new Product(
				"product name",
				"12345654323456",
				BigDecimal.valueOf(25),
				newBrand(),
				newProductType()
		);
	}

	public ProductType newProductType() {
		return new ProductType(
				"cream"
		);
	}

	public Transaction newTransaction() {
		return new Transaction(
				LocalDateTime.of(2021, 1, 3, 13, 10),
				newShop()
		);
	}

	public Shop newShop() {
		return new Shop(
				"shop name",
				newAddress()
		);
	}

}
// TODO: 14.04.2021 statyczne
