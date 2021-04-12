package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.model.Shop;

import java.time.LocalDate;

public class EmployeeDto {

	private Long id;
	private String name;
	private String surname;
	private String telephoneNumber;
	private LocalDate dateOfEmployment;
	private String email;
	private Long shopId;

	public EmployeeDto(Long id, String name, String surname, String telephoneNumber, LocalDate dateOfEmployment, String email, Long shopId) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.telephoneNumber = telephoneNumber;
		this.dateOfEmployment = dateOfEmployment;
		this.email = email;
		this.shopId = shopId;
	}

	@JsonCreator
	public EmployeeDto(
			@JsonProperty("name") String name,
			@JsonProperty("surname") String surname,
			@JsonProperty("telephoneNumber") String telephoneNumber,
			@JsonProperty("dateOfEmployment") LocalDate dateOfEmployment,
			@JsonProperty("email") String email,
			@JsonProperty("shopId") Long shopId) {
		this.name = name;
		this.surname = surname;
		this.telephoneNumber = telephoneNumber;
		this.dateOfEmployment = dateOfEmployment;
		this.email = email;
		this.shopId = shopId;
	}

	public static EmployeeDto from(Employee employee) {
		return new EmployeeDto(
				employee.getId(),
				employee.getName(),
				employee.getSurname(),
				employee.getTelephoneNumber(),
				employee.getDateOfEmployment(),
				employee.getEmail(),
				employee.getShop().getId()
		);
	}

	public Employee toEntity(Shop shop) {
		return new Employee(name, surname, telephoneNumber, dateOfEmployment, email, shop);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public LocalDate getDateOfEmployment() {
		return dateOfEmployment;
	}

	public String getEmail() {
		return email;
	}

	public Long getShopId() {
		return shopId;
	}
}
