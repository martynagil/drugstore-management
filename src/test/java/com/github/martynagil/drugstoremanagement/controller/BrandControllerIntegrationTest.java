package com.github.martynagil.drugstoremanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.martynagil.drugstoremanagement.IntegrationTestBase;
import com.github.martynagil.drugstoremanagement.dto.BrandDto;
import com.github.martynagil.drugstoremanagement.model.Producer;
import com.github.martynagil.drugstoremanagement.repositories.ProducerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class BrandControllerIntegrationTest extends IntegrationTestBase {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProducerRepository producerRepository;

	@AfterEach
	void tearDown() {
		producerRepository.deleteAll();
	}

	@Test
	void shouldNotAddBrandWhenBodyIsEmpty() throws Exception {
		mockMvc.perform(post("/brands"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldAddBrandWhenRequestHasBody() throws Exception {
		Producer producer = producerRepository.save(new Producer(
				"name",
				"emaol@sdfc.pl",
				"1234543234"
		));

		BrandDto brandDto = new BrandDto(
				"name",
				"mail",
				"2345678765",
				producer.getId()
		);
		mockMvc.perform(
				post("/brands")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(brandDto))
		)
				.andExpect(status().isOk());

	}
}
