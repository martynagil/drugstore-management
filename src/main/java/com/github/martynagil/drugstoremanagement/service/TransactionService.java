package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.TransactionDto;
import com.github.martynagil.drugstoremanagement.model.Transaction;
import com.github.martynagil.drugstoremanagement.model.TransactionEntry;
import com.github.martynagil.drugstoremanagement.model.TransactionEntryId;
import com.github.martynagil.drugstoremanagement.repositories.ProductRepository;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import com.github.martynagil.drugstoremanagement.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class TransactionService {

	private ShopRepository shopRepository;
	private TransactionRepository transactionRepository;
	private ProductRepository productRepository;

	public TransactionService(ShopRepository shopRepository, TransactionRepository transactionRepository, ProductRepository productRepository) {
		this.shopRepository = shopRepository;
		this.transactionRepository = transactionRepository;
		this.productRepository = productRepository;
	}

	@Transactional
	public void addTransaction(TransactionDto transactionDto) {
		Transaction transaction = createTransactionFromDto(transactionDto);
		transactionRepository.save(transaction);
	}

	private Transaction createTransactionFromDto(TransactionDto transactionDto) {
		Transaction transaction = new Transaction(
				transactionDto.getSubmissionTime(),
				shopRepository.findById(transactionDto.getShopId())
						.orElseThrow(EntityNotFoundException::new)
		);
		transactionDto.getTransactionEntries().stream()
				.map(entry -> new TransactionEntry(
						new TransactionEntryId(
								productRepository.findById(entry.getProductId())
										.orElseThrow(EntityNotFoundException::new),
								transaction
						),
						entry.getCount()
				)).forEach(transaction::addEntry);

		return transaction;
	}
}
