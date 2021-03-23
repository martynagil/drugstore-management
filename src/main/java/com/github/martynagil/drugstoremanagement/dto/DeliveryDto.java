package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.martynagil.drugstoremanagement.model.DeliveryEntry;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryDto {

	private Long id;
	private LocalDateTime time;
	private Long shopId;
	private List<DeliveryEntry> deliveryEntries;

	public DeliveryDto(Long id, LocalDateTime time, Long shopId, List<DeliveryEntry> deliveryEntries) {
		this.id = id;
		this.time = time;
		this.shopId = shopId;
		this.deliveryEntries = deliveryEntries;
	}

	@JsonCreator
	public DeliveryDto(
			@JsonProperty("time") LocalDateTime time,
			@JsonProperty("shopId") Long shopId,
			@JsonProperty("deliveryEntries") List<DeliveryEntry> deliveryEntries) {
		this.id = id;
		this.time = time;
		this.shopId = shopId;
		this.deliveryEntries = deliveryEntries;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public Long getShopId() {
		return shopId;
	}

	public List<DeliveryEntry> getDeliveryEntries() {
		return deliveryEntries;
	}
}
