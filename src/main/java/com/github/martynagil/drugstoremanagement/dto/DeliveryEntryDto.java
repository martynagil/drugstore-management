package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.martynagil.drugstoremanagement.model.DeliveryEntryId;

public class DeliveryEntryDto {

	private Long productId;
	private int count;

	@JsonCreator
	public DeliveryEntryDto(
			@JsonProperty("productId") Long productId,
			@JsonProperty("count") int count) {
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
