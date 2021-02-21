package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.controller.ComplaintDto;
import com.github.martynagil.drugstoremanagement.controller.ComplaintUpdateDto;
import com.github.martynagil.drugstoremanagement.exceptions.ComplaintAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Complaint;
import com.github.martynagil.drugstoremanagement.model.ComplaintStatus;
import com.github.martynagil.drugstoremanagement.repositories.ComplaintRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProductRepository;
import com.github.martynagil.drugstoremanagement.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class ComplaintService {

    private ComplaintRepository complaintRepository;
    private ProductRepository productRepository;
    private TransactionRepository transactionRepository;


    public ComplaintService(ComplaintRepository complaintRepository, ProductRepository productRepository, TransactionRepository transactionRepository) {
        this.complaintRepository = complaintRepository;
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    public void addComplaint(ComplaintDto complaintDto) {
        if (complaintExists(complaintDto)) {
            throw new ComplaintAlreadyExistsException();
        }

        Complaint complaint = createComplainFromDto(complaintDto);
        complaintRepository.save(complaint);
    }

    @Transactional
    public void updateComplaint(ComplaintUpdateDto complaintUpdateDto, Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(EntityNotFoundException::new);
        complaint.updateStatus(complaintUpdateDto.getComplaintStatus());
    }

    private Boolean complaintExists(ComplaintDto complaintDto) {
        return complaintRepository.existsByTransactionIdAndProductIdAndComplaintStatus(
                        complaintDto.getTransactionId(),
                        complaintDto.getProductId(),
                        ComplaintStatus.SUBMITTED);
    }

    private Complaint createComplainFromDto(ComplaintDto complaintDto) {
        return new Complaint(
                complaintDto.getSubmissionDate(),
                complaintDto.getReason(),
                productRepository.findById(complaintDto.getProductId())
                        .orElseThrow(EntityNotFoundException::new),
                transactionRepository.findById(complaintDto.getTransactionId())
                        .orElseThrow(EntityNotFoundException::new)
        );
    }
}
