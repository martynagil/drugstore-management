package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.ProducerDto;
import com.github.martynagil.drugstoremanagement.dto.ProductDto;
import com.github.martynagil.drugstoremanagement.exceptions.ProductAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Brand;
import com.github.martynagil.drugstoremanagement.model.Producer;
import com.github.martynagil.drugstoremanagement.model.Product;
import com.github.martynagil.drugstoremanagement.model.ProductType;
import com.github.martynagil.drugstoremanagement.repositories.BrandRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProductRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProductTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	@Mock
	private BrandRepository brandRepository;
	@Mock
	private ProductTypeRepository productTypeRepository;
	@InjectMocks
	private ProductService productService = new ProductService(productRepository, brandRepository, productTypeRepository);
	@Captor
	ArgumentCaptor<Product> productCaptor;

	@Test
	void shouldAddProductWhenItDoesNotExist() {
		when(productRepository.existsByBarcode(any()))
				.thenReturn(false);
		when(brandRepository.findById(any()))
				.thenReturn(Optional.of(brand()));
		when(productTypeRepository.findById(any()))
				.thenReturn(Optional.of(productType()));

		productService.addProduct(productDto());
		verify(productRepository).save(productCaptor.capture());
		Product product = productCaptor.getValue();

		assertThat(product.getName()).isEqualTo(productDto().getName());
		assertThat(product.getBarcode()).isEqualTo(productDto().getBarcode());
		assertThat(product.getPrice()).isEqualTo(productDto().getPrice());
	}

	@Test
	void shouldNotAddProductWhenIdExist() {
		when(productRepository.existsByBarcode(any()))
				.thenReturn(true);

		assertThrows(
				ProductAlreadyExistsException.class,
				() -> productService.addProduct(productDto())
		);
		verify(productRepository, never()).save(any());
	}

	private Producer producer() {
		return new Producer(
				"producer",
				"producer@sdcv.com",
				"09876543"
		);
	}

	private Brand brand() {
		return new Brand(
				"brand",
				"ertghjmnbv@sdc.com",
				"7654321",
				producer()
		);
	}

	private ProductType productType() {
		return new ProductType(
				"cream"
		);
	}

	private ProductDto productDto() {
		return new ProductDto(
				"name",
				"12345678",
				BigDecimal.valueOf(12.9),
				brand().getId(),
				productType().getId()
		);
	}
}
