package com.github.martynagil.drugstoremanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.martynagil.drugstoremanagement.EntityFactory;
import com.github.martynagil.drugstoremanagement.IntegrationTestBase;
import com.github.martynagil.drugstoremanagement.dto.ComplaintDto;
import com.github.martynagil.drugstoremanagement.model.Address;
import com.github.martynagil.drugstoremanagement.model.Product;
import com.github.martynagil.drugstoremanagement.model.Shop;
import com.github.martynagil.drugstoremanagement.model.Transaction;
import com.github.martynagil.drugstoremanagement.repositories.ProductRepository;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import com.github.martynagil.drugstoremanagement.repositories.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ComplainControllerIntegrationTest extends IntegrationTestBase {

	private static final LocalDate SUBMISSION_DATE = LocalDate.of(2021, 12, 13);
	private static final LocalDateTime SUBMISSION_TIME = LocalDateTime.of(2021, 12, 13, 13, 40);
	private EntityFactory entityFactory = new EntityFactory();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ShopRepository shopRepository;

	@Test
	void shouldNotAddComplaintIfBodyIsEmpty() throws Exception {
		mockMvc.perform(post("/complaints"))
				.andExpect(status().isBadRequest());
	}

	@AfterEach
	void tearDown() {
		transactionRepository.deleteAll();
		productRepository.deleteAll();
	}

	@Test
	void shouldAddComplaintWhenRequestHasBody() throws Exception {
		Shop shop = entityFactory.newShop();
		shop = shopRepository.save(shop);
		Transaction transaction = transaction(shop);
		transactionRepository.save(transaction);
		Product product = product();
		productRepository.save(product);
		ComplaintDto complaintDto = complaintDto();

		mockMvc.perform(
				post("/complaints")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(complaintDto))
		)
				.andExpect(status().isOk());
	}

	private ComplaintDto complaintDto() {
		return new ComplaintDto(
				SUBMISSION_DATE,
				"big reason",
				transactionRepository.findAll().get(0).getId(),
				productRepository.findAll().get(0).getId()
		);
	}

	private Transaction transaction(Shop shop) {
		return new Transaction(
				SUBMISSION_TIME,
				shop
		);
	}

	private Product product() {
		return new Product(
				"name",
				"3456784345678",
				BigDecimal.valueOf(13),
				entityFactory.newBrand(),
				entityFactory.newProductType()
		);
	}
}

// TODO: 14.04.2021 zapisac do repozytoriow przypisac z save i je przekazac
