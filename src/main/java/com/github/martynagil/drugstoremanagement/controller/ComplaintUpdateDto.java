package com.github.martynagil.drugstoremanagement.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.martynagil.drugstoremanagement.model.ComplaintStatus;

public class ComplaintUpdateDto {

    private ComplaintStatus complaintStatus;

    @JsonCreator
    public ComplaintUpdateDto(@JsonProperty("complaintStatus") ComplaintStatus complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public ComplaintStatus getComplaintStatus() {
        return complaintStatus;
    }
}
