package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.SalaryDto;
import com.github.martynagil.drugstoremanagement.exceptions.SalaryAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.model.Salary;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import com.github.martynagil.drugstoremanagement.repositories.SalaryRepository;
import com.sun.xml.bind.v2.runtime.property.UnmarshallerChain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalaryServiceTest {

	private static final Long EMPLOYEE_ID = 1L;
	private static final LocalDate EMPLOYMENT_DATE = LocalDate.now();
	private static final YearMonth SALARY_MONTH = YearMonth.parse("2021-03");

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private SalaryRepository salaryRepository;

	@InjectMocks
	private SalaryService salaryService = new SalaryService(employeeRepository, salaryRepository);

	@Captor
	private ArgumentCaptor<Salary> salaryCaptor;

	@Test
	void shouldAddSalaryWhenItNotExist() {
		Employee employee = employee();
		SalaryDto salaryDto = salaryDto();
		when(employeeRepository.findById(EMPLOYEE_ID))
				.thenReturn(Optional.of(employee));
		when(salaryRepository.existsByMonth(YearMonth.parse("2021-03")))
				.thenReturn(false);

		salaryService.addSalary(EMPLOYEE_ID, salaryDto);

		verify(salaryRepository).save(salaryCaptor.capture());
		Salary salary = salaryCaptor.getValue();

		assertThat(salary.getAmount()).isEqualTo(salaryDto.getAmount());
		assertThat(salary.getMonth()).isEqualTo(salaryDto.getMonth());
		assertThat(employee.getSalaries().get(0)).isEqualTo(salary);
	}

	@Test
	void shouldNotAddSalaryWhenItExists() {
		Employee employee = employee();
		when(employeeRepository.findById(EMPLOYEE_ID))
				.thenReturn(Optional.of(employee));
		when(salaryRepository.existsByMonth(YearMonth.parse("2021-03")))
				.thenReturn(true);

		assertThrows(
				SalaryAlreadyExistsException.class,
				() -> salaryService.addSalary(EMPLOYEE_ID, salaryDto())
		);
		verify(salaryRepository, never()).save(any());
	}

	private Employee employee() {
		return new Employee(
				"name",
				"surname",
				"789564342",
				EMPLOYMENT_DATE,
				"sdfg@sdf.com",
				null
				);
	}

	private SalaryDto salaryDto() {
		return new SalaryDto(
				SALARY_MONTH,
				BigDecimal.valueOf(9000)
		);
	}
}
