package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.EmployeeDto;
import com.github.martynagil.drugstoremanagement.model.Address;
import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.model.Shop;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	private final EmployeeDto employeeDto = new EmployeeDto(
			"name",
			"surname",
			"number",
			LocalDate.parse("2018-01-07"),
			"mail",
			1L
	);
	@Mock
	EmployeeRepository employeeRepository;
	@Mock
	ShopRepository shopRepository;
	@InjectMocks
	EmployeeService employeeService = new EmployeeService(employeeRepository, shopRepository);
	@Captor
	ArgumentCaptor<Employee> employeeCaptor;

	@Test
	void shouldAddNewEmployee() {
		when(shopRepository.findById(any()))
				.thenReturn(Optional.of(shop()));

		employeeService.addNewEmployee(employeeDto);
		verify(employeeRepository).save(employeeCaptor.capture());
		Employee employee = employeeCaptor.getValue();

		assertThat(employee.getDateOfEmployment()).isEqualTo(employeeDto.getDateOfEmployment());
		assertThat(employee.getEmail()).isEqualTo(employeeDto.getEmail());
		assertThat(employee.getName()).isEqualTo(employeeDto.getName());
		assertThat(employee.getSurname()).isEqualTo(employeeDto.getSurname());
		assertThat(employee.getTelephoneNumber()).isEqualTo(employeeDto.getTelephoneNumber());
	}

	@Test
	void dismissEmployee() {
		when(employeeRepository.findById(any()))
				.thenReturn(Optional.of(employee(employeeDto)));

		// TODO: 30.03.2021  
	}

	private Address address() {
		return new Address(
				"Wrocław",
				"50-306",
				"Krzywoustego 110"
		);
	}

	private Shop shop() {
		return new Shop(
				"shop name",
				address()
		);
	}

	private Employee employee(EmployeeDto employeeDto) {
		return new Employee("name", 
				"surname", 
				"telephoneNumber", 
				LocalDate.now(), 
				"email", 
				shop());
	}
}
