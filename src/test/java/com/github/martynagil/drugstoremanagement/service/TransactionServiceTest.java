package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.TransactionDto;
import com.github.martynagil.drugstoremanagement.dto.TransactionEntryDto;
import com.github.martynagil.drugstoremanagement.model.Shop;
import com.github.martynagil.drugstoremanagement.model.Transaction;
import com.github.martynagil.drugstoremanagement.model.TransactionEntry;
import com.github.martynagil.drugstoremanagement.repositories.ProductRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

	private static final Long SHOP_ID = 1L;
	private static final Long PRODUCT_ID_1 = 2L;
	private static final Long PRODUCT_ID_2 = 3L;
	private static final LocalDateTime TRANSACTION_TIME = LocalDateTime.now();

	@Mock
	private ShopRepository shopRepository;

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private TransactionService transactionService = new TransactionService(shopRepository, transactionRepository, productRepository);

	@Test
	void shouldAddTransaction() {
		TransactionDto transactionDto = transactionDto();
		Shop shop = shop();
		when(shopRepository.findById(SHOP_ID))
				.thenReturn(Optional.of(shop));

		// TODO: 02.04.2021  
		transactionService.addTransaction(transactionDto);
	}

	private TransactionDto transactionDto() {
		return new TransactionDto(
				TRANSACTION_TIME,
				SHOP_ID,
				transactionEntries()
		);
	}

	private Shop shop() {
		return new Shop(
				"name",
				null
		);
	}

	private List<TransactionEntryDto> transactionEntries() {
		return Arrays.asList(
				new TransactionEntryDto(PRODUCT_ID_1, 5),
				new TransactionEntryDto(PRODUCT_ID_2, 7)
		);
	}
}
