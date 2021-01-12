package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.model.WorkTime;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class WorkTimeService {

    EmployeeRepository employeeRepository;

    public WorkTimeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void startWork(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException());
        employee.startWork();
    }

    @Transactional
    public void endWork(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException());
        employee.endWork();
    }

    
    public List<WorkTime> getMonthlyWorkTimes(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException())
                .getWorkTimes()
                .stream()
                .filter(workTime -> {
                    return workTime.getEndDate().getMonth().equals(LocalDateTime.now().getMonth());
                })
                .collect(toList()
                );
    }
}
