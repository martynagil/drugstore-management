package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionEntryDto {

	private Long productId;
	private int count;

	@JsonCreator
	public TransactionEntryDto(
			@JsonProperty Long productId,
			@JsonProperty int count) {
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
