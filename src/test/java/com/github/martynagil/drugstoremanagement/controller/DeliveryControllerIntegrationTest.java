package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.IntegrationTestBase;
import com.github.martynagil.drugstoremanagement.dto.DeliveryDto;
import com.github.martynagil.drugstoremanagement.dto.DeliveryEntryDto;
import com.github.martynagil.drugstoremanagement.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.github.martynagil.drugstoremanagement.EntityFactory.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeliveryControllerIntegrationTest extends IntegrationTestBase {

	@Test
	void shouldCreateDeliveryWhenRequestHasBody() throws Exception {
		Producer producer = newProducer();
		producer = producerRepository.save(producer);
		Brand brand = newBrand(producer);
		brand = brandRepository.save(brand);
		ProductType productType = newProductType();
		productType = productTypeRepository.save(productType);
		Product product = newProduct(brand, productType);
		product = productRepository.save(product);
		Address address = newAddress();
		Shop shop = newShop(address);
		shop = shopRepository.save(shop);
		DeliveryEntryDto deliveryEntryDto1 = newDeliveryEntryDto(product.getId());
		DeliveryEntryDto deliveryEntryDto2 = newDeliveryEntryDto(product.getId());
		DeliveryDto deliveryDto = deliveryDto(shop, deliveryEntryDto1, deliveryEntryDto2);

		mockMvc.perform(
				post("/deliveries")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(deliveryDto))
		)
				.andExpect(status().isOk());
	}

	@Test
	void shouldNotCreateDeliveryWhenBodyIsEmpty() throws Exception {
		mockMvc.perform(post("/deliveries"))
				.andExpect(status().isBadRequest());
	}

	private DeliveryDto deliveryDto(Shop shop, DeliveryEntryDto deliveryEntryDto1, DeliveryEntryDto deliveryEntryDto2) {
		return new DeliveryDto(
				LocalDateTime.of(2021, 2, 15, 15, 17),
				shop.getId(),
				deliveryEntryDtos(deliveryEntryDto1, deliveryEntryDto2)
		);
	}

	private List<DeliveryEntryDto> deliveryEntryDtos(DeliveryEntryDto deliveryEntryDto1, DeliveryEntryDto deliveryEntryDto2) {
		return Arrays.asList(deliveryEntryDto1, deliveryEntryDto2);
	}

}
