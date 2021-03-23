package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_entries")
public class TransactionEntry {

	@EmbeddedId
	private TransactionEntryId transactionEntryId;

	@Column(nullable = false)
	private int count;

	@Deprecated
	protected TransactionEntry() {
	}

	public TransactionEntry(TransactionEntryId transactionEntryId, int count) {
		this.transactionEntryId = transactionEntryId;
		this.count = count;
	}

	public TransactionEntryId getTransactionEntryId() {
		return transactionEntryId;
	}

	public int getCount() {
		return count;
	}


}
