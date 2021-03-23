package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.BrandDto;
import com.github.martynagil.drugstoremanagement.exceptions.BrandAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.repositories.BrandRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProducerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandServiceTest {

	private final BrandDto brandDto = new BrandDto(
			1L, "name", "mail", "1111111", 2L
	);

	private BrandRepository brandRepository;
	private ProducerRepository producerRepository;
	private BrandService brandService;

	@BeforeEach
	void setUp() {
		brandRepository = Mockito.mock(BrandRepository.class);
		producerRepository = Mockito.mock(ProducerRepository.class);
		brandService = new BrandService(brandRepository, producerRepository);
	}

	@Test
	void shouldNotAddBrandWhenItExists() {
		when(brandRepository.existsByNameAndProducerId(any(), any()))
				.thenReturn(true);

		assertThrows(
				BrandAlreadyExistsException.class,
				() -> brandService.addBrand(brandDto)
		);

		verify(brandRepository, never()).save(any());
	}
}
