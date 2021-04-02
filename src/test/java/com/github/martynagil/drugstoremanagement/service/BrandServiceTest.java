package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.BrandDto;
import com.github.martynagil.drugstoremanagement.exceptions.BrandAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Brand;
import com.github.martynagil.drugstoremanagement.model.Producer;
import com.github.martynagil.drugstoremanagement.repositories.BrandRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProducerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

	@Mock
	private BrandRepository brandRepository;

	@Mock
	private ProducerRepository producerRepository;

	@InjectMocks
	private BrandService brandService = new BrandService(brandRepository, producerRepository);

	@Captor
	private ArgumentCaptor<Brand> brandCaptor;

	@Test
	void shouldNotAddBrandWhenItExists() {
		when(brandRepository.existsByNameAndProducerId(any(), any()))
				.thenReturn(true);

		assertThrows(
				BrandAlreadyExistsException.class,
				() -> brandService.addBrand(brandDto())
		);
		verify(brandRepository, never()).save(any());
	}

	@Test
	void shouldAddBrandWhenItNotExist() {
		BrandDto brandDto = brandDto();
		when(brandRepository.existsByNameAndProducerId(any(), any()))
				.thenReturn(false);
		Producer producer = new Producer("producer", "meail", "2345678");
		when(producerRepository.findById(brandDto.getProducerId()))
				.thenReturn(Optional.of(producer));

		brandService.addBrand(brandDto);

		verify(brandRepository).save(brandCaptor.capture());
		Brand brand = brandCaptor.getValue();

		assertThat(brand.getName()).isEqualTo(brandDto.getName());
		assertThat(brand.getEmail()).isEqualTo(brandDto.getEmail());
		assertThat(brand.getTelephone()).isEqualTo(brandDto.getTelephone());
	}

	private BrandDto brandDto() {
		return new BrandDto(
				1L, "name", "mail", "1111111", 2L
		);
	}
}
