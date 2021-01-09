package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class WorkTimeService {

    EmployeeRepository employeeRepository;

    public WorkTimeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void startWork(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException());
        employee.startWork();
    }

    public void endWork(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException());
        employee.endWork();
    }
}
