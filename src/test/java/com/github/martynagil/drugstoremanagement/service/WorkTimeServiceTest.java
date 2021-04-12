package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkTimeServiceTest {

	private static final LocalDateTime START_TIME = LocalDateTime.of(2021, 12, 3, 10, 15, 0);
	private static final LocalDateTime END_TIME = START_TIME.plusHours(1L);

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private Clock clock;

	@InjectMocks
	private WorkTimeService workTimeService;

	@Test
	void shouldSetStartTime() {
		Employee employee = employee();
		when(clock.now())
				.thenReturn(START_TIME);
		when(employeeRepository.findById(any()))
				.thenReturn(Optional.of(employee));

		workTimeService.startWork(1L);

		assertThat(employee.getWorkTimes().get(0).getStartDate()).isEqualTo(START_TIME);
	}

	@Test
	void shouldSetEndTime() {
		Employee employee = employee();
		when(clock.now())
				.thenReturn(END_TIME);
		when(employeeRepository.findById(any()))
				.thenReturn(Optional.of(employee));

		workTimeService.startWork(1L);
		workTimeService.endWork(1L);

		assertThat(employee.getWorkTimes().get(0).getEndDate()).isEqualTo(END_TIME);
	}

	@Test
	void shouldNotSetEndTime() {
		Employee employee = employee();
		when(employeeRepository.findById(any()))
				.thenReturn(Optional.of(employee));

		assertThrows(
				EntityNotFoundException.class,
				() -> workTimeService.endWork(1L)
		);
		verify(employeeRepository, never()).save(any());
	}

	private Employee employee() {
		return new Employee(
				"name",
				"surname",
				"12345678",
				LocalDate.parse("2018-12-02"),
				"email",
				null
		);
	}
}
