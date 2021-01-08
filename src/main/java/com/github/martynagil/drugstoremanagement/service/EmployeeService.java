package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.controller.EmployeeDto;
import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public void addNewEmployee(EmployeeDto employeeDto) {
        Employee employee = createEmployeeFromDto(employeeDto);
        repository.save(employee);
    }

//    public List<Employee> getCurrentEmployees() {
//        return getAllEmployees().stream().
//    }

    public void dismissEmployee(EmployeeDto employeeDto) {
//        employeeDto.setDateOfDismissal();
    }


    private Employee createEmployeeFromDto(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getName(),
                employeeDto.getSurname(),
                employeeDto.getTelephoneNumber(),
                employeeDto.getDateOfEmployment(),
                employeeDto.getEmail(),
                employeeDto.getShop()
        );
    }
}
