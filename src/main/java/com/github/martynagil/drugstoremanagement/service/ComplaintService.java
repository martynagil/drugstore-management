package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.controller.ComplaintDto;
import com.github.martynagil.drugstoremanagement.controller.ComplaintUpdateDto;
import com.github.martynagil.drugstoremanagement.model.Complaint;
import com.github.martynagil.drugstoremanagement.repositories.ComplaintRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProductRepository;
import com.github.martynagil.drugstoremanagement.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ComplaintService {

    private ComplaintRepository complaintRepository;
    private ProductRepository productRepository;
    private TransactionRepository transactionRepository;


    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public void addComplaint(ComplaintDto complaintDto) {
        Complaint complaint = createComplainFromDto(complaintDto);
        complaintRepository.save(complaint);
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

    public void updateComplaint(ComplaintUpdateDto complaintUpdateDto, Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(EntityNotFoundException::new);
        complaint.updateStatus(complaintUpdateDto.getComplaintStatus());
        complaintRepository.save(complaint);
    }
}
