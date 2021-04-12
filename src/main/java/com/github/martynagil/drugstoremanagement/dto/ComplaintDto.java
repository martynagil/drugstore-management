package com.github.martynagil.drugstoremanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.martynagil.drugstoremanagement.model.Complaint;

import java.time.LocalDate;

public class ComplaintDto {

	private Long id;
	private LocalDate submissionDate;
	private String reason;
	private Long transactionId;
	private Long productId;

	public ComplaintDto(Long id, LocalDate submissionDate, String reason, Long transactionId, Long productId) {
		this.id = id;
		this.submissionDate = submissionDate;
		this.reason = reason;
		this.transactionId = transactionId;
		this.productId = productId;
	}

	@JsonCreator
	public ComplaintDto(
			@JsonProperty("submissionDate") LocalDate submissionDate,
			@JsonProperty("reason") String reason,
			@JsonProperty("transactionId") Long transactionId,
			@JsonProperty("productId") Long productId) {
		this.submissionDate = submissionDate;
		this.reason = reason;
		this.transactionId = transactionId;
		this.productId = productId;
	}

	public static ComplaintDto from(Complaint complaint) {
		return new ComplaintDto(
				complaint.getId(),
				complaint.getSubmissionDate(),
				complaint.getReason(),
				complaint.getTransaction().getId(),
				complaint.getProduct().getId()
		);
	}

	public Long getId() {
		return id;
	}

	public LocalDate getSubmissionDate() {
		return submissionDate;
	}

	public String getReason() {
		return reason;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public Long getProductId() {
		return productId;
	}
}
