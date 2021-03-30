package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.ProducerDto;
import com.github.martynagil.drugstoremanagement.exceptions.ProducerAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Producer;
import com.github.martynagil.drugstoremanagement.repositories.ProducerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProducerServiceTest {

	private final ProducerDto producerDto = new ProducerDto(
			"name",
			"email",
			"telephone"
	);
	@Mock
	private ProducerRepository producerRepository;
	@InjectMocks
	private ProducerService producerService = new ProducerService(producerRepository);
	@Captor
	ArgumentCaptor<Producer> producerCaptor;

	@Test
	void shouldAddProducerWhenItNotExists() {
		when(producerRepository.existsByName(any()))
				.thenReturn(false);

		producerService.addProducer(producerDto);
		verify(producerRepository).save(producerCaptor.capture());
		Producer producer = producerCaptor.getValue();

		assertThat(producer.getName()).isEqualTo(producerDto.getName());
		assertThat(producer.getEmail()).isEqualTo(producerDto.getEmail());
		assertThat(producer.getTelephone()).isEqualTo(producerDto.getTelephone());
	}

	@Test
	void shouldNotAddProducerWhenItExists() {
		when(producerRepository.existsByName(any()))
				.thenReturn(true);

		assertThrows(
				ProducerAlreadyExistsException.class,
				() -> producerService.addProducer(producerDto)
		);
		verify(producerRepository, never()).save(any());
	}


}
