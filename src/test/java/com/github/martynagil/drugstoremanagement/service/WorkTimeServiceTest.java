package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkTimeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private WorkTimeService workTimeService = new WorkTimeService(employeeRepository);

// TODO: 02.04.2021

	private void findEmployee() {
		when(employeeRepository.findById(any()))
				.thenReturn(Optional.of(new Employee(
						"name",
						"surname",
						"12345678",
						LocalDate.parse("2018-12-02"),
						"email",
						null
				)));
	}
}
