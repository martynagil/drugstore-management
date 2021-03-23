package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.martynagil.drugstoremanagement.model.TransactionEntry;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionDto {

	private Long id;
	private LocalDateTime submissionTime;
	private Long shopId;
	private List<TransactionEntry> transactionEntries;

	public TransactionDto(Long id, LocalDateTime submissionTime, Long shopId, List<TransactionEntry> transactionEntries) {
		this.id = id;
		this.submissionTime = submissionTime;
		this.shopId = shopId;
		this.transactionEntries = transactionEntries;
	}

	@JsonCreator
	public TransactionDto(
			@JsonProperty("submissionTime") LocalDateTime submissionTime,
			@JsonProperty("shopId") Long shopId,
			@JsonProperty("transactionEntries") List<TransactionEntry> transactionEntries) {
		this.submissionTime = submissionTime;
		this.shopId = shopId;
		this.transactionEntries = transactionEntries;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getSubmissionTime() {
		return submissionTime;
	}

	public Long getShopId() {
		return shopId;
	}

	public List<TransactionEntry> getTransactionEntries() {
		return transactionEntries;
	}
}
