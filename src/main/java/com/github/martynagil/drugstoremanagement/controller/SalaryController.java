package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.model.Salary;
import com.github.martynagil.drugstoremanagement.service.SalaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/employees")
public class SalaryController {

    private SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @PostMapping("/{employeeId}/salaries")
    public void addSalary(@PathVariable Long employeeId, @RequestBody SalaryDto salaryDto) {
        salaryService.addSalary(employeeId, salaryDto);
    }

    @GetMapping("/{employeeId}/salaries")
    public List<SalaryDto> getAnnualSalaries(@PathVariable Long employeeId) {
        return salaryService.getAnnualSalaries(employeeId).stream()
                .map(salary -> SalaryDto.from(salary))
                .collect(toList());
    }
}
