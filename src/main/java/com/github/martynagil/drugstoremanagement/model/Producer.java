package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "producers")
public class Producer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String telephone;

	@Deprecated
	protected Producer() {
	}

	public Producer(String name, String email, String telephone) {
		this.name = name;
		this.email = email;
		this.telephone = telephone;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getTelephone() {
		return telephone;
	}
}
