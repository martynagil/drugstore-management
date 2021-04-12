package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.ShopDto;
import com.github.martynagil.drugstoremanagement.model.Shop;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ShopServiceTest {

	@Mock
	private ShopRepository shopRepository;

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private ShopService shopService;

	@Captor
	private ArgumentCaptor<Shop> shopCaptor;

	@Test
	void shouldAddNewShop() {
		ShopDto shopDto = shopDto();

		shopService.addNewShop(shopDto);

		verify(shopRepository).save(shopCaptor.capture());
		Shop shop = shopCaptor.getValue();

		assertThat(shop.getName()).isEqualTo(shopDto.getName());
		assertThat(shop.getAddress().getCity()).isEqualTo(shopDto.getCity());
		assertThat(shop.getAddress().getPostCode()).isEqualTo(shopDto.getPostCode());
		assertThat(shop.getAddress().getStreetAndNumber()).isEqualTo(shopDto.getStreetAndNumber());
	}

	private ShopDto shopDto() {
		return new ShopDto(
				"name",
				"city",
				"postcode",
				"street and number"
		);
	}
}
