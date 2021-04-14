package com.github.martynagil.drugstoremanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.martynagil.drugstoremanagement.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class IntegrationTestBase {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected TransactionRepository transactionRepository;

	@Autowired
	protected ProductRepository productRepository;

	@Autowired
	protected ShopRepository shopRepository;

	@Autowired
	protected BrandRepository brandRepository;

	@Autowired
	protected ProductTypeRepository productTypeRepository;

	@Autowired
	protected ProducerRepository producerRepository;

	@Autowired
	protected ComplaintRepository complaintRepository;

	@Autowired
	protected DeliveryRepository deliveryRepository;

	@Autowired
	protected EmployeeRepository employeeRepository;

	@Autowired
	protected SalaryRepository salaryRepository;

	@Autowired
	protected WorkTimeRepository workTimeRepository;

	@AfterEach
	void tearDown() {
		complaintRepository.deleteAll();
		transactionRepository.deleteAll();
		productRepository.deleteAll();
		shopRepository.deleteAll();
		brandRepository.deleteAll();
		productRepository.deleteAll();
		producerRepository.deleteAll();
		productTypeRepository.deleteAll();
		deliveryRepository.deleteAll();
		employeeRepository.deleteAll();;
		salaryRepository.deleteAll();
		workTimeRepository.deleteAll();
	}
}
