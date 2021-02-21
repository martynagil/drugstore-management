package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.service.ComplaintService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping
    public void addComplaint(@RequestBody ComplaintDto complaintDto) {
        complaintService.addComplaint(complaintDto);
    }

    @PutMapping("/{complaintId}")
    public void updateStatusOfComplaint(@RequestBody ComplaintUpdateDto complaintUpdateDto, @PathVariable Long complaintId) {
        complaintService.updateComplaint(complaintUpdateDto, complaintId);
    }

}
