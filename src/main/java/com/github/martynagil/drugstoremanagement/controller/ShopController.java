package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.dto.EmployeeDto;
import com.github.martynagil.drugstoremanagement.dto.ShopDto;
import com.github.martynagil.drugstoremanagement.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/shops")
public class ShopController {

	private ShopService shopService;

	public ShopController(ShopService shopService) {
		this.shopService = shopService;
	}

	@GetMapping
	public List<ShopDto> getShops() {
		return shopService.getAllShops().stream()
				.map(shop -> ShopDto.from(shop))
				.collect(toList());
	}

	@GetMapping("/{shopId}/employees")
	public List<EmployeeDto> getEmployees(@PathVariable Long shopId) {
		return shopService.getEmployees(shopId).stream()
				.map(employee -> EmployeeDto.from(employee))
				.collect(toList());
	}

	@PostMapping
	public void addShop(@RequestBody ShopDto shopDto) {
		shopService.addNewShop(shopDto);
	}
}
