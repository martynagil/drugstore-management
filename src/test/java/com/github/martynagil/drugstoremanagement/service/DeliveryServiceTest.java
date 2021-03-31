package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.DeliveryDto;
import com.github.martynagil.drugstoremanagement.model.Address;
import com.github.martynagil.drugstoremanagement.model.Delivery;
import com.github.martynagil.drugstoremanagement.model.DeliveryEntry;
import com.github.martynagil.drugstoremanagement.model.Shop;
import com.github.martynagil.drugstoremanagement.repositories.DeliveryRepository;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

	@Mock
	private ShopRepository shopRepository;

	@Mock
	private DeliveryRepository deliveryRepository;

	@InjectMocks
	private DeliveryService deliveryService = new DeliveryService(shopRepository, deliveryRepository);

	@Captor
	private ArgumentCaptor<Delivery> deliveryCaptor;

	@Test
	void shouldCreateDelivery() {
		DeliveryDto deliveryDto = new DeliveryDto(
				LocalDateTime.parse("2021-01-03T10:15:35"),
				1L,
				deliveryEntries());
		when(shopRepository.findById(any()))
				.thenReturn(Optional.of(shop()));

		deliveryService.createDelivery(deliveryDto);

		verify(deliveryRepository).save(deliveryCaptor.capture());
		Delivery delivery = deliveryCaptor.getValue();

		assertThat(delivery.getDeliveryEntries()).isEqualTo(deliveryDto.getDeliveryEntries());
		assertThat(delivery.getTime()).isEqualTo(deliveryDto.getTime());
	}

	private Shop shop() {
		return new Shop(
				"shop name",
				null
		);
	}

	private List<DeliveryEntry> deliveryEntries() {
		return Arrays.asList(
				new DeliveryEntry(
						null,
						4
				),
				new DeliveryEntry(
						null,
						5
				));
	}
}
