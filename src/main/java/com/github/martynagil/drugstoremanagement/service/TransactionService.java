package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.TransactionDto;
import com.github.martynagil.drugstoremanagement.model.Transaction;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class TransactionService {

    private ShopRepository shopRepository;

    public TransactionService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Transactional
    public void addTransaction(TransactionDto transactionDto) {
        Transaction transaction = createTransactionFromDto(transactionDto);
    }

    private Transaction createTransactionFromDto(TransactionDto transactionDto) {
        return new Transaction(
                transactionDto.getSubmissionTime(),
                shopRepository.findById(transactionDto.getShopId())
                .orElseThrow(EntityNotFoundException::new),
                transactionDto.getTransactionEntries()
        );
    }

}
