package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.IntegrationTestBase;
import com.github.martynagil.drugstoremanagement.dto.BrandDto;
import com.github.martynagil.drugstoremanagement.exceptions.BrandAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Brand;
import com.github.martynagil.drugstoremanagement.model.Producer;
import com.github.martynagil.drugstoremanagement.repositories.BrandRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProducerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BrandServiceIntegrationTest extends IntegrationTestBase {

	private static final String NAME1 = "name1";
	private static final String NAME2 = "name2";
	private Long producerId;
	private Producer producer = new Producer("nameProducer", "email", "telephone");

	@Autowired
	private BrandService brandService;

	@Autowired
	private ProducerRepository producerRepository;

	@Autowired
	private BrandRepository brandRepository;

	@BeforeEach
	void setUp() {
		producerRepository.save(producer);
		producerId = producerRepository.findAll().get(0).getId();
	}

	@AfterEach
	void tearDown() {
		brandRepository.deleteAll();
		producerRepository.deleteAll();
	}

	@Test
	void shouldAddBrand() {
		BrandDto brandDto = brandDto("name1");

		brandService.addBrand(brandDto);

		Brand brand = brandRepository.findAll().get(0);
		assertThat(brand.getName())
				.isEqualTo(brandDto.getName());
		assertThat(brand.getEmail())
				.isEqualTo(brandDto.getEmail());
		assertThat(brand.getTelephone())
				.isEqualTo(brandDto.getTelephone());
		assertThat(brand.getProducer().getId())
				.isEqualTo(brandDto.getProducerId());
	}

	@Test
	void shouldNotAddBrandWhenItExists() {
		BrandDto brandDto = brandDto("name2");
		brandRepository.save(new Brand(
				brandDto.getName(),
				brandDto.getEmail(),
				brandDto.getTelephone(),
				producer
		));

		assertThrows(
				BrandAlreadyExistsException.class,
				() -> brandService.addBrand(brandDto)
		);
		assertThat(brandRepository.findAll()).hasSize(1);
	}

	private BrandDto brandDto(String name) {
		return new BrandDto(
				name,
				"email@1.com",
				"234567876",
				producerId
		);
	}

}
