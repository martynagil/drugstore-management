package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.ComplaintDto;
import com.github.martynagil.drugstoremanagement.dto.ComplaintUpdateDto;
import com.github.martynagil.drugstoremanagement.exceptions.ComplaintAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.*;
import com.github.martynagil.drugstoremanagement.repositories.ComplaintRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProductRepository;
import com.github.martynagil.drugstoremanagement.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ComplaintServiceTest {

	private final ComplaintDto complaintDto = new ComplaintDto(
			1L, LocalDate.parse("2020-11-03"), "reason reason", 1L, 1L
	);
	private final Producer producer = new Producer(
			"producer",
			"producer@sdcv.com",
			"09876543"
	);
	private final Brand brand = new Brand(
			"brand",
			"ertghjmnbv@sdc.com",
			"7654321",
			producer
	);
	private final ProductType productType = new ProductType(
			"cream"
	);
	private final Product product = new Product(
			"name",
			"234567887",
			BigDecimal.valueOf(25.5),
			brand,
			productType
	);
	private final Address address = new Address(
			"Wrocław",
			"50-306",
			"Krzywoustego 110"
	);
	private final Shop shop = new Shop(
			"shop name",
			address
	);
	private final List<TransactionEntry> transactionEntries = new ArrayList<>();
	private final Transaction transaction = new Transaction(
			LocalDateTime.now(),
			shop,
			transactionEntries
	);
	private ComplaintUpdateDto complaintUpdateDto = new ComplaintUpdateDto(
			ComplaintStatus.ACCEPTED
	);
	private Complaint complaint = new Complaint(
			LocalDate.parse("2021-01-13"),
			"reason",
			product,
			transaction
	);
	private ComplaintRepository complaintRepository;
	private ProductRepository productRepository;
	private TransactionRepository transactionRepository;
	private ComplaintService complaintService;

	@BeforeEach
	void setUp() {
		complaintRepository = mock(ComplaintRepository.class);
		productRepository = mock(ProductRepository.class);
		transactionRepository = mock(TransactionRepository.class);
		complaintService = new ComplaintService(complaintRepository, productRepository, transactionRepository);
	}

	@Test
	void shouldAddComplaintWhenItDoesNotExist() {
		when(complaintRepository.existsByTransactionIdAndProductIdAndComplaintStatus(any(), any(), any()))
				.thenReturn(false);
		when(productRepository.findById(any()))
				.thenReturn(Optional.of(product));
		when(transactionRepository.findById(any()))
				.thenReturn(Optional.of(transaction));

		complaintService.addComplaint(complaintDto);

		verify(complaintRepository).save(any());
	}

	@Test
	void shouldNotAddComplaintWhenIdDoesExist() {
		when(complaintRepository.existsByTransactionIdAndProductIdAndComplaintStatus(any(), any(), any()))
				.thenReturn(true);

		assertThrows(
				ComplaintAlreadyExistsException.class,
				() -> complaintService.addComplaint(complaintDto)
		);

		verify(complaintRepository, never()).save(any());
	}

	@Test
	void shouldNotUpdateWhenComplaintDoesNotExists() {
		when(complaintRepository.findById(any()))
				.thenReturn(Optional.empty());

		assertThrows(
				EntityNotFoundException.class,
				() -> complaintService.updateComplaint(complaintUpdateDto, 1L)
		);

		verify(complaintRepository, never()).save(any());
	}

	@Test
	void shouldUpdateWhenComplaintExists() {
		when(complaintRepository.findById(any()))
				.thenReturn(Optional.of(complaint));

		complaintService.updateComplaint(complaintUpdateDto, complaint.getId());

		assertThat(complaint.getComplaintStatus()).isEqualByComparingTo(complaintUpdateDto.getComplaintStatus());
	}
}
