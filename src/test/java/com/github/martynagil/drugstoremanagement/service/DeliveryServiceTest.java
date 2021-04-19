package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.DeliveryDto;
import com.github.martynagil.drugstoremanagement.dto.DeliveryEntryDto;
import com.github.martynagil.drugstoremanagement.model.Delivery;
import com.github.martynagil.drugstoremanagement.model.Product;
import com.github.martynagil.drugstoremanagement.model.Shop;
import com.github.martynagil.drugstoremanagement.repositories.DeliveryRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProductRepository;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

	private static final Long SHOP_ID = 1L;
	private static final Long PRODUCT_ID_1 = 2L;
	private static final Long PRODUCT_ID_2 = 3L;
	private static final LocalDateTime TRANSACTION_TIME = LocalDateTime.now();

	@Mock
	private ShopRepository shopRepository;

	@Mock
	private DeliveryRepository deliveryRepository;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private DeliveryService deliveryService;

	@Captor
	private ArgumentCaptor<Delivery> deliveryCaptor;

	@Test
	void shouldCreateDelivery() {
		DeliveryDto deliveryDto = new DeliveryDto(
				TRANSACTION_TIME,
				SHOP_ID,
				deliveryEntries());
		Shop shop = shop();
		when(shopRepository.findById(SHOP_ID))
				.thenReturn(Optional.of(shop));
		Product product1 = product("namee");
		Product product2 = product("name");
		when(productRepository.findById(PRODUCT_ID_1))
				.thenReturn(Optional.of(product1));
		when(productRepository.findById(PRODUCT_ID_2))
				.thenReturn(Optional.of(product2));

		deliveryService.createDelivery(deliveryDto);

		verify(deliveryRepository).save(deliveryCaptor.capture());
		Delivery delivery = deliveryCaptor.getValue();

		assertThat(delivery.getDeliveryEntries().get(0).getCount()).isEqualTo(deliveryDto.getDeliveryEntryDtos().get(0).getCount());
		assertThat(delivery.getDeliveryEntries().get(1).getCount()).isEqualTo(deliveryDto.getDeliveryEntryDtos().get(1).getCount());
		assertThat(delivery.getTime()).isEqualTo(deliveryDto.getTime());
	}

	private Shop shop() {
		return new Shop(
				"shop name",
				null
		);
	}

	private List<DeliveryEntryDto> deliveryEntries() {
		return Arrays.asList(
				new DeliveryEntryDto(
						PRODUCT_ID_1,
						4
				),
				new DeliveryEntryDto(
						PRODUCT_ID_2,
						5
				));
	}

	private Product product(String name) {
		return new Product(
				name,
				"12345673456",
				BigDecimal.valueOf(98),
				null,
				null
		);
	}
}
