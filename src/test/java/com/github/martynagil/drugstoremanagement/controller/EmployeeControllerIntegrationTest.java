package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.IntegrationTestBase;
import com.github.martynagil.drugstoremanagement.dto.EmployeeDto;
import com.github.martynagil.drugstoremanagement.model.Address;
import com.github.martynagil.drugstoremanagement.model.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static com.github.martynagil.drugstoremanagement.EntityFactory.newAddress;
import static com.github.martynagil.drugstoremanagement.EntityFactory.newShop;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeControllerIntegrationTest extends IntegrationTestBase {

	private static final LocalDate DATE_OF_EMPLOYMENT = LocalDate.of(2021, 3, 4);

	@Test
	void shouldAddEmployeeWhenRequestHasBody() throws Exception {
		Address address = newAddress();
		Shop shop = newShop(address);
		shop = shopRepository.save(shop);
		EmployeeDto employeeDto = employeeDto(shop);

		mockMvc.perform(
				post("/employees")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(employeeDto))
		)
				.andExpect(status().isOk());
	}

	@Test
	void shouldNotAddEmployeeWhenBodyIsEmpty() throws Exception {
		mockMvc.perform(
				post("/employees"))
				.andExpect(status().isBadRequest());
	}
//
//	@Test
//	void shouldDismissEmployee

	private EmployeeDto employeeDto(Shop shop) {
		return new EmployeeDto(
				"name",
				"surname",
				"12345654",
				DATE_OF_EMPLOYMENT,
				"email@employee.com",
				shop.getId()
		);
	}
}
