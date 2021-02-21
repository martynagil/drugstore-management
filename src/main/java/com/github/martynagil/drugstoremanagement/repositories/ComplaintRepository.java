package com.github.martynagil.drugstoremanagement.repositories;

import com.github.martynagil.drugstoremanagement.model.Complaint;
import com.github.martynagil.drugstoremanagement.model.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    Boolean existsByTransactionIdAndProductIdAndComplaintStatus(Long transactionId, Long productId, ComplaintStatus status);
}
