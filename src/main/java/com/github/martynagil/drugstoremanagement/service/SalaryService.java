package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.controller.SalaryDto;
import com.github.martynagil.drugstoremanagement.model.Salary;
import com.github.martynagil.drugstoremanagement.repositories.SalaryRepository;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    private SalaryRepository salaryRepository;

    public SalaryService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public void addSalary(Long employeeId, SalaryDto salaryDto) {
        Salary salary = createSalaryFromDto(salaryDto);
        salaryRepository.save(salary);
    }

    private Salary createSalaryFromDto(SalaryDto salaryDto) {
        return new Salary(
                salaryDto.getYear(),
                salaryDto.getMonth(),
                salaryDto.getAmount()
        );
    }
}

// TODO: 09.01.2021 połączyć salary z employee
