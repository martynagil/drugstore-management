package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.ShopDto;
import com.github.martynagil.drugstoremanagement.model.Address;
import com.github.martynagil.drugstoremanagement.model.Employee;
import com.github.martynagil.drugstoremanagement.model.Shop;
import com.github.martynagil.drugstoremanagement.repositories.EmployeeRepository;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

	private ShopRepository shopRepository;
	private EmployeeRepository employeeRepository;

	public ShopService(ShopRepository shopRepository, EmployeeRepository employeeRepository) {
		this.shopRepository = shopRepository;
		this.employeeRepository = employeeRepository;
	}

	public List<Shop> getAllShops() {
		return shopRepository.findAll();
	}

	public void addNewShop(ShopDto shopDto) {
		Shop shop = createShopFromDto(shopDto);
		shopRepository.save(shop);
	}

	public List<Employee> getEmployees(Long shopId) {
		return employeeRepository.findAllByDateOfDismissalIsNullAndShopId(shopId);
	}

	private Shop createShopFromDto(ShopDto shopDto) {
		return new Shop(
				shopDto.getName(),
				new Address(
						shopDto.getCity(),
						shopDto.getPostCode(),
						shopDto.getStreetAndNumber()
				)
		);
	}
}
