package com.github.martynagil.drugstoremanagement;

import com.github.martynagil.drugstoremanagement.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EntityFactory {

	public static Address newAddress() {
		return new Address(
				"wroclaw",
				"56-988",
				"krzywoustego 110"
		);
	}

	public static Brand newBrand(Producer producer) {
		return new Brand(
				"brand name",
				"sdf@sdf.com",
				"23456345",
				producer
		);
	}

	public static Complaint newComplaint(Shop shop, Product product, Transaction transaction) {
		return new Complaint(
				LocalDate.of(2021, 12, 30),
				"very serious reason",
				product,
				transaction
		);
	}

	public  static Delivery newDelivery(List<DeliveryEntry> deliveryEntries, Address address, Shop shop) {
		return new Delivery(
				LocalDateTime.of(2021, 3, 4, 5, 4),
				shop,
				deliveryEntries
		);
	}

	public static Producer newProducer() {
		return new Producer(
				"producer name",
				"email@producer.com",
				"23456543"
		);
	}

	public static Product newProduct(Brand brand, ProductType productType) {
		return new Product(
				"product name",
				"12345654323456",
				BigDecimal.valueOf(25),
				brand,
				productType
		);
	}

	public static ProductType newProductType() {
		return new ProductType(
				"cream"
		);
	}

	public static Transaction newTransaction(Shop shop) {
		return new Transaction(
				LocalDateTime.of(2021, 1, 3, 13, 10),
				shop
		);
	}

	public static Shop newShop(Address address) {
		return new Shop(
				"shop name",
				address
		);
	}

	public static DeliveryEntryId newDeliveryEntryId(Product product, Delivery delivery) {
		return new DeliveryEntryId(
				product,
				delivery
		);
	}

	public static DeliveryEntry newDeliveryEntry(DeliveryEntryId deliveryEntryId, int count) {
		return new DeliveryEntry(
				deliveryEntryId,
				count
		);
	}

}
