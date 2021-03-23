package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime submissionTime;

	@ManyToOne(optional = false)
	private Shop shop;

	@OneToMany(mappedBy = "transactionEntryId.transaction")
	private List<TransactionEntry> transactionEntries = new ArrayList<>();

	@Deprecated
	protected Transaction() {
	}

	public Transaction(LocalDateTime submissionTime, Shop shop, List<TransactionEntry> transactionEntries) {
		this.submissionTime = submissionTime;
		this.shop = shop;
		this.transactionEntries = transactionEntries;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getSubmissionTime() {
		return submissionTime;
	}

	public Shop getShop() {
		return shop;
	}

	public List<TransactionEntry> getTransactionEntries() {
		return transactionEntries;
	}
}
