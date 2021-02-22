package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.ProductTypeDto;
import com.github.martynagil.drugstoremanagement.exceptions.ProductTypeAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.ProductType;
import com.github.martynagil.drugstoremanagement.repositories.ProductTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeService {

    ProductTypeRepository productTypeRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }


    public void addProductType(ProductTypeDto productTypeDto) {
        if (productTypeExists(productTypeDto)) {
            throw new ProductTypeAlreadyExistsException();
        }

        ProductType productType = createProductTypeFromDto(productTypeDto);
        productTypeRepository.save(productType);
    }

    private ProductType createProductTypeFromDto(ProductTypeDto productTypeDto) {
        return new ProductType(productTypeDto.getName());
    }

    private boolean productTypeExists(ProductTypeDto productTypeDto) {
        return productTypeRepository.existsByName(productTypeDto.getName());
    }
}
