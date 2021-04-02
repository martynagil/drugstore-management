package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.TransactionDto;
import com.github.martynagil.drugstoremanagement.model.Transaction;
import com.github.martynagil.drugstoremanagement.model.TransactionEntry;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import com.github.martynagil.drugstoremanagement.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

	@Mock
	private ShopRepository shopRepository;

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private TransactionService transactionService = new TransactionService(shopRepository, transactionRepository);

	@Test
	void shouldAddTransaction() {
		TransactionDto transactionDto = transactionDto();
		// TODO: 02.04.2021  

		transactionService.addTransaction(transactionDto);
	}

	private TransactionDto transactionDto() {
		return new TransactionDto(
				LocalDateTime.now(),
				null,
				transactionEntries()
		);
	}

	private List<TransactionEntry> transactionEntries() {
		return Arrays.asList(
				new TransactionEntry(null, 5),
				new TransactionEntry(null, 7)
		);
	}
}
