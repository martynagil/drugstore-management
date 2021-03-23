package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.SalaryDto;
import com.github.martynagil.drugstoremanagement.exceptions.SalaryAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.model.Salary;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import com.github.martynagil.drugstoremanagement.repositories.SalaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.YearMonth;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class SalaryService {

	private EmployeeRepository employeeRepository;
	private SalaryRepository salaryRepository;

	public SalaryService(EmployeeRepository employeeRepository, SalaryRepository salaryRepository) {
		this.employeeRepository = employeeRepository;
		this.salaryRepository = salaryRepository;
	}

	@Transactional
	public void addSalary(Long employeeId, SalaryDto salaryDto) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EntityNotFoundException());

		if (salaryExists(salaryDto)) {
			throw new SalaryAlreadyExistsException("Month: " + salaryDto.getMonth());
		}

		Salary salary = createSalaryFromDto(salaryDto);
		employee.addSalary(salary);
		// TODO: 22.03.2021
	}

	public List<Salary> getAnnualSalaries(Long employeeId) {
		return employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EntityNotFoundException())
				.getSalaries()
				.stream()
				.filter(salary -> {
					return salary.getMonth().getYear() == YearMonth.now().getYear();
				})
				.collect(toList());
	}

	private Salary createSalaryFromDto(SalaryDto salaryDto) {
		return new Salary(
				salaryDto.getMonth(),
				salaryDto.getAmount()
		);
	}

	private Boolean salaryExists(SalaryDto salaryDto) {
		return salaryRepository.existsByMonth(salaryDto.getMonth());
	}
}
