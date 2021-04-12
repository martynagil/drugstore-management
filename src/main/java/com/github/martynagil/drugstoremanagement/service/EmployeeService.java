package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.EmployeeDismissalDto;
import com.github.martynagil.drugstoremanagement.dto.EmployeeDto;
import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.model.Shop;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void addNewEmployee(EmployeeDto employeeDto) {
		Shop shop = shopRepository.findById(employeeDto.getShopId())
				.orElseThrow(() -> new EntityNotFoundException());

		employeeRepository.save(employeeDto.toEntity(shop));
	}

	@Transactional
	public void dismissEmployee(Long employeeId, EmployeeDismissalDto employeeDismissalDto) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EntityNotFoundException());
		employee.dismiss(employeeDismissalDto.getDismissalDate());
	}
}
