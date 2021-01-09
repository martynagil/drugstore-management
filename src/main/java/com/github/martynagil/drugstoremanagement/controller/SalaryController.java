package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.service.SalaryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {

    private SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @PostMapping("/employees/{employeeId}/salaries")
    public void addSalary(@PathVariable Long employeeId, @RequestBody SalaryDto salaryDto) {
        salaryService.addSalary(employeeId, salaryDto);
    }
}
