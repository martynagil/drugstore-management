package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.IntegrationTestBase;
import com.github.martynagil.drugstoremanagement.model.*;
import org.junit.jupiter.api.Test;

import static com.github.martynagil.drugstoremanagement.EntityFactory.*;

public class DeliveryControllerIntegrationTest extends IntegrationTestBase {

	@Test
	void shouldCreateDeliveryWhenRequestHasBody() {
		Producer producer = newProducer();
		producer = producerRepository.save(producer);
		Brand brand = newBrand(producer);
		brand = brandRepository.save(brand);
		ProductType productType = newProductType();
		productType = productTypeRepository.save(productType);
		Product product = newProduct(brand, productType);
		DeliveryEntry deliveryEntry1 = newDeliveryEntry(, 4);
		// TODO: 14.04.2021 del entry 2 lista i dokonczyc
		Delivery delivery = newDelivery();
		DeliveryEntryId deliveryEntryId1 = newDeliveryEntryId(product, )
	}
}
