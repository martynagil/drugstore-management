package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String postCode;

	@Column(nullable = false)
	private String streetAndNumber;

	@Deprecated
	protected Address() {
	}

	public Address(String city, String postCode, String streetAndNumber) {
		this.city = city;
		this.postCode = postCode;
		this.streetAndNumber = streetAndNumber;
	}

	public Long getId() {
		return id;
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
