package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.dto.EmployeeDismissalDto;
import com.github.martynagil.drugstoremanagement.dto.EmployeeDto;
import com.github.martynagil.drugstoremanagement.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public List<EmployeeDto> getEmployees() {
		return employeeService.getAllEmployees().stream()
				.map(employee -> EmployeeDto.from(employee))
				.collect(toList());
	}

	@PostMapping
	public void addEmployee(@RequestBody EmployeeDto employeeDto) {
		employeeService.addNewEmployee(employeeDto);
	}

	@PutMapping("/{employeeId}")
	public void dismissEmployee(@RequestBody EmployeeDismissalDto employeeDismissalDto, @PathVariable Long employeeId) {
		employeeService.dismissEmployee(employeeId, employeeDismissalDto);
	}
}
