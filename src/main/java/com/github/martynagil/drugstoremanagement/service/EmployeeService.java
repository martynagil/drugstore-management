package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.controller.EmployeeDismissalDto;
import com.github.martynagil.drugstoremanagement.controller.EmployeeDto;
import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.model.Shop;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ShopRepository shopRepository;

    public EmployeeService(EmployeeRepository employeeRepository, ShopRepository shopRepository) {
        this.employeeRepository = employeeRepository;
        this.shopRepository = shopRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void addNewEmployee(EmployeeDto employeeDto) {
        Shop shop = shopRepository.findById(employeeDto.getShopId())
                .orElseThrow(() -> new EntityNotFoundException());
        employeeRepository.save(employeeDto.toEntity(shop));
    }

    public void dismissEmployee(Long employeeId, EmployeeDismissalDto employeeDismissalDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException());
        if (employee.getDateOfDismissal().equals(null)) {
            employee.dismiss(employeeDismissalDto.getDismissalDate());
            employeeRepository.save(employee);
        }
    }
}
