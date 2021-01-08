package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<EmployeeDto> getEmployees() {
        return employeeService.getAllEmployees().stream()
                .map(employee -> EmployeeDto.from((employee)))
                .collect(Collectors.toList());
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.addNewEmployee(employeeDto);
    }

    @PutMapping("/employees")
    public void dismissEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.dismissEmployee(employeeDto);
    }
}
