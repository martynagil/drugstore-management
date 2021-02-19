package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.controller.SalaryDto;
import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.model.Salary;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.YearMonth;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class SalaryService {

    private EmployeeRepository employeeRepository;

    public SalaryService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @Transactional
    public void addSalary(Long employeeId, SalaryDto salaryDto) {
        Salary salary = createSalaryFromDto(salaryDto);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException());
        employee.addSalary(salary);
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
}
