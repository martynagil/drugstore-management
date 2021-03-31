package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.ComplaintDto;
import com.github.martynagil.drugstoremanagement.dto.ComplaintUpdateDto;
import com.github.martynagil.drugstoremanagement.exceptions.ComplaintAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.exceptions.IllegalStatusUpdateException;
import com.github.martynagil.drugstoremanagement.model.*;
import com.github.martynagil.drugstoremanagement.repositories.ComplaintRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProductRepository;
import com.github.martynagil.drugstoremanagement.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComplaintServiceTest {

	@Mock
	private ComplaintRepository complaintRepository;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private ComplaintService complaintService = new ComplaintService(complaintRepository, productRepository, transactionRepository);

	@Test
	void shouldAddComplaintWhenItDoesNotExist() {
		complaintDoesNotExistForAdding();
		findProductInRepository();
		findTransactionInRepository();

		complaintService.addComplaint(complaintDto());

		verify(complaintRepository).save(any());
	}

	@Test
	void shouldNotAddComplaintWhenIdDoesExist() {
		complaintExistsForAdding();

		assertThrows(
				ComplaintAlreadyExistsException.class,
				() -> complaintService.addComplaint(complaintDto())
		);
		verify(complaintRepository, never()).save(any());
	}

	@Test
	void shouldNotUpdateWhenComplaintDoesNotExists() {
		complaintDoesNotExistForUpdating();

		assertThrows(
				EntityNotFoundException.class,
				() -> complaintService.updateComplaint(complaintUpdateToAcceptedDto(), 1L)
		);
		verify(complaintRepository, never()).save(any());
	}

	@Test
	void shouldUpdateWhenSubmittedComplaintExists() {
		complaintExistsForUpdating(complaint());
		Complaint complaint = complaintRepository.findById(1L)
				.orElseThrow(EntityNotFoundException::new);

		complaintService.updateComplaint(complaintUpdateToAcceptedDto(), 1L);

		assertThat(complaint.getComplaintStatus()).isEqualTo(complaintUpdateToAcceptedDto().getComplaintStatus());
	}

	@Test
	void shouldNotUpdateToAcceptedWhenStatusIsRejected() {
		complaintExistsForUpdating(complaint());
		complaintService.updateComplaint(complaintUpdateToRejectedDto(), 1L);

		assertThrows(
				IllegalStatusUpdateException.class,
				() -> complaintService.updateComplaint(complaintUpdateToAcceptedDto(), 1L)
		);
	}

	@Test
	void shouldNotUpdateToRejectedWhenStatusIsAccepted() {
		complaintExistsForUpdating(complaint());
		complaintService.updateComplaint(complaintUpdateToAcceptedDto(), 1L);

		assertThrows(
				IllegalStatusUpdateException.class,
				() -> complaintService.updateComplaint(complaintUpdateToRejectedDto(), 1L)
		);
	}

	@Test
	void shouldNotUpdateToSubmittedWhenStatusIsAccepted() {
		complaintExistsForUpdating(complaint());
		complaintService.updateComplaint(complaintUpdateToAcceptedDto(), 1L);

		assertThrows(
				IllegalStatusUpdateException.class,
				() -> complaintService.updateComplaint(complaintUpdateToSubmittedDto(), 1L)
		);
	}

	@Test
	void shouldNotUpdateToSubmittedWhenStatusIsRejected() {
		complaintExistsForUpdating(complaint());
		complaintService.updateComplaint(complaintUpdateToRejectedDto(), 1L);

		assertThrows(
				IllegalStatusUpdateException.class,
				() -> complaintService.updateComplaint(complaintUpdateToSubmittedDto(), 1L)
		);
	}

	private void findTransactionInRepository() {
		when(transactionRepository.findById(any()))
				.thenReturn(Optional.of(transaction()));
	}

	private void findProductInRepository() {
		when(productRepository.findById(any()))
				.thenReturn(Optional.of(product()));
	}

	private void complaintDoesNotExistForAdding() {
		when(complaintRepository.existsByTransactionIdAndProductIdAndComplaintStatus(any(), any(), any()))
				.thenReturn(false);
	}

	private void complaintExistsForAdding() {
		when(complaintRepository.existsByTransactionIdAndProductIdAndComplaintStatus(any(), any(), any()))
				.thenReturn(true);
	}

	private void complaintDoesNotExistForUpdating() {
		when(complaintRepository.findById(any()))
				.thenReturn(Optional.empty());
	}

	private void complaintExistsForUpdating(Complaint complaint) {
		when(complaintRepository.findById(any()))
				.thenReturn(Optional.of(complaint));
	}

	private ComplaintDto complaintDto() {
		return new ComplaintDto(
				1L, LocalDate.parse("2020-11-03"), "reason reason", 1L, 1L
		);
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

	private Product product() {
		return new Product(
				"name",
				"234567887",
				BigDecimal.valueOf(25.5),
				brand(),
				productType()
		);
	}

	private Address address() {
		return new Address(
				"Wrocław",
				"50-306",
				"Krzywoustego 110"
		);
	}

	private Shop shop() {
		return new Shop(
				"shop name",
				address()
		);
	}

	private Transaction transaction() {
		return new Transaction(
				LocalDateTime.now(),
				shop(),
				new ArrayList<>()
		);
	}

	private ComplaintUpdateDto complaintUpdateToAcceptedDto() {
		return new ComplaintUpdateDto(
				ComplaintStatus.ACCEPTED
		);
	}

	private ComplaintUpdateDto complaintUpdateToRejectedDto() {
		return new ComplaintUpdateDto(
				ComplaintStatus.REJECTED
		);
	}

	private ComplaintUpdateDto complaintUpdateToSubmittedDto() {
		return new ComplaintUpdateDto(
				ComplaintStatus.SUBMITTED
		);
	}

	private Complaint complaint() {
		return new Complaint(
				LocalDate.parse("2021-01-13"),
				"reason",
				product(),
				transaction()
		);
	}
}
