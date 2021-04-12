package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.dto.ProductTypeDto;
import com.github.martynagil.drugstoremanagement.service.ProductTypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-types")
public class ProductTypeController {

	private ProductTypeService productTypeService;

	public ProductTypeController(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	@PostMapping
	public void addProductType(@RequestBody ProductTypeDto productTypeDto) {
		productTypeService.addProductType(productTypeDto);
	}
}
