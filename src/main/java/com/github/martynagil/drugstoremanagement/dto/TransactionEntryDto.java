package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TransactionEntryDto {

	private Long productId;
	private int count;

	public TransactionEntryDto(Long productId, int count) {
		this.productId = productId;
		this.count = count;
	}

	public Long getProductId() {
		return productId;
	}

	public int getCount() {
		return count;
	}
}
