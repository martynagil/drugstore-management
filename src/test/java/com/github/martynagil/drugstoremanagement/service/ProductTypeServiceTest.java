package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.ProductTypeDto;
import com.github.martynagil.drugstoremanagement.exceptions.ProductTypeAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.ProductType;
import com.github.martynagil.drugstoremanagement.repositories.ProductTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductTypeServiceTest {

	@Mock
	private ProductTypeRepository productTypeRepository;

	@InjectMocks
	private ProductTypeService productTypeService = new ProductTypeService(productTypeRepository);

	@Captor
	private ArgumentCaptor<ProductType> productTypeCaptor;

	@Test
	void shouldNotAddProductTypeWhenItExists() {
		when(productTypeRepository.existsByName(any()))
				.thenReturn(true);

		assertThrows(
				ProductTypeAlreadyExistsException.class,
				() -> productTypeService.addProductType(productTypeDto())
		);

		verify(productTypeRepository, never()).save(any());
	}

	@Test
	void shouldAddProductTypeWhenItDoesNotExist() {
		ProductTypeDto productTypeDto = productTypeDto();
		when(productTypeRepository.existsByName(any()))
				.thenReturn(false);

		productTypeService.addProductType(productTypeDto);

		verify(productTypeRepository).save(productTypeCaptor.capture());
		ProductType productType = productTypeCaptor.getValue();

		assertThat(productType.getName()).isEqualTo(productTypeDto.getName());
	}

	private ProductTypeDto productTypeDto() {
		return new ProductTypeDto("name");
	}
}
