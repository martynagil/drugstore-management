package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryDto {

	private Long id;
	private LocalDateTime time;
	private Long shopId;
	private List<DeliveryEntryDto> deliveryEntryDtos;

	public DeliveryDto(Long id, LocalDateTime time, Long shopId, List<DeliveryEntryDto> deliveryEntryDtos) {
		this.id = id;
		this.time = time;
		this.shopId = shopId;
		this.deliveryEntryDtos = deliveryEntryDtos;
	}

	@JsonCreator
	public DeliveryDto(
			@JsonProperty("time") LocalDateTime time,
			@JsonProperty("shopId") Long shopId,
			@JsonProperty("deliveryEntries") List<DeliveryEntryDto> deliveryEntryDtos) {
		this.time = time;
		this.shopId = shopId;
		this.deliveryEntryDtos = deliveryEntryDtos;
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

	public List<DeliveryEntryDto> getDeliveryEntryDtos() {
		return deliveryEntryDtos;
	}
}
