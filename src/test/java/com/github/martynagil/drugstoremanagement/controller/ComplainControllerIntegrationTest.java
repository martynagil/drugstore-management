package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.IntegrationTestBase;
import com.github.martynagil.drugstoremanagement.dto.ComplaintDto;
import com.github.martynagil.drugstoremanagement.dto.ComplaintUpdateDto;
import com.github.martynagil.drugstoremanagement.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static com.github.martynagil.drugstoremanagement.EntityFactory.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComplainControllerIntegrationTest extends IntegrationTestBase {

	private static final LocalDate SUBMISSION_DATE1 = LocalDate.of(2021, 12, 13);
	private static final LocalDate SUBMISSION_DATE2 = LocalDate.of(2021, 12, 14);

	@Test
	void shouldNotAddComplaintIfBodyIsEmpty() throws Exception {
		mockMvc.perform(post("/complaints"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldAddComplaintWhenRequestHasBody() throws Exception {
		Address address = newAddress();
		Shop shop = newShop(address);
		shop = shopRepository.save(shop);
		Transaction transaction = newTransaction(shop);
		transactionRepository.save(transaction);
		Producer producer = newProducer();
		producer = producerRepository.save(producer);
		Brand brand = newBrand(producer);
		brand = brandRepository.save(brand);
		ProductType productType = newProductType();
		productType = productTypeRepository.save(productType);
		Product product = newProduct(brand, productType);
		productRepository.save(product);
		ComplaintDto complaintDto = complaintDto();

		mockMvc.perform(
				post("/complaints")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(complaintDto))
		)
				.andExpect(status().isOk());
	}

	@Test
	void shouldNotUpdateComplaintWhenItDoesNotExist() throws Exception {
		mockMvc.perform(put("/complaints/1"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldUpdateComplaintWhenItExists() throws Exception {
		Address address = newAddress();
		Shop shop = newShop(address);
		shop = shopRepository.save(shop);
		Transaction transaction = newTransaction(shop);
		transactionRepository.save(transaction);
		Producer producer = newProducer();
		producer = producerRepository.save(producer);
		Brand brand = newBrand(producer);
		brand = brandRepository.save(brand);
		ProductType productType = newProductType();
		productType = productTypeRepository.save(productType);
		Product product = newProduct(brand, productType);
		productRepository.save(product);
		Complaint complaint = newComplaint(shop, product, transaction);
		complaint = complaintRepository.save(complaint);

		mockMvc.perform(
				put("/complaints/" + complaint.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(complaintUpdateDto()))
		)
				.andExpect(status().isOk());
	}

	private ComplaintDto complaintDto() {
		return new ComplaintDto(
				SUBMISSION_DATE1,
				"big reason",
				transactionRepository.findAll().get(0).getId(),
				productRepository.findAll().get(0).getId()
		);
	}

	private ComplaintUpdateDto complaintUpdateDto() {
		return new ComplaintUpdateDto(
				ComplaintStatus.ACCEPTED
		);
	}

}
