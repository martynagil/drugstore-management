package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.BrandDto;
import com.github.martynagil.drugstoremanagement.exceptions.BrandAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Producer;
import com.github.martynagil.drugstoremanagement.repositories.BrandRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProducerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandServiceTest {

	private final BrandDto brandDto = new BrandDto(
			1L, "name", "mail", "1111111", 2L
	);

	@Mock
	private BrandRepository brandRepository;
	@Mock
	private ProducerRepository producerRepository;
	@InjectMocks
	BrandService brandService = new BrandService(brandRepository, producerRepository);

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

	@Test
	void shouldAddBrandWhenItNotExist() {
		when(brandRepository.existsByNameAndProducerId(any(), any()))
				.thenReturn(false);
		Producer producer = new Producer("producer", "meail", "2345678");
		when(producerRepository.findById(brandDto.getProducerId()))
				.thenReturn(Optional.of(producer));

		brandService.addBrand(brandDto);

		verify(brandRepository).save(any());

		// TODO: 24.03.2021 argument captor
	}
}
