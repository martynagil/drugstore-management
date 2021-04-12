package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.martynagil.drugstoremanagement.model.Shop;

public class ShopDto {

	private Long id;
	private String name;
	private String city;
	private String postCode;
	private String streetAndNumber;

	public ShopDto(Long id, String name, String city, String postCode, String streetAndNumber) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.postCode = postCode;
		this.streetAndNumber = streetAndNumber;
	}

	@JsonCreator
	public ShopDto(
			@JsonProperty("name") String name,
			@JsonProperty("city") String city,
			@JsonProperty("postCode") String postCode,
			@JsonProperty("streetAndNumber") String streetAndNumber) {
		this.name = name;
		this.city = city;
		this.postCode = postCode;
		this.streetAndNumber = streetAndNumber;
	}

	public static ShopDto from(Shop shop) {
		return new ShopDto(
				shop.getId(),
				shop.getName(),
				shop.getAddress().getCity(),
				shop.getAddress().getPostCode(),
				shop.getAddress().getStreetAndNumber()
		);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getStreetAndNumber() {
		return streetAndNumber;
	}
}
