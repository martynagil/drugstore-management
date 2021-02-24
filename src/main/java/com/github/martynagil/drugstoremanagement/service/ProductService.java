package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.ProductDto;
import com.github.martynagil.drugstoremanagement.exceptions.ProductTypeAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Product;
import com.github.martynagil.drugstoremanagement.repositories.BrandRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProductRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProductTypeRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private ProductTypeRepository productTypeRepository;

    public void addProduct(ProductDto productDto) {
        if (productExists(productDto)) {
            throw new ProductTypeAlreadyExistsException();
        }

        Product product = createProductFromDto(productDto);
        productRepository.save(product);
    }

    private Product createProductFromDto(ProductDto productDto) {
        return new Product(
                productDto.getName(),
                productDto.getBarcode(),
                productDto.getPrice(),
                brandRepository.findById(productDto.getBrandId())
                        .orElseThrow(EntityNotFoundException::new),
                productTypeRepository.findById(productDto.getProductTypeId())
                        .orElseThrow(EntityNotFoundException::new)
        );
    }

    private boolean productExists(ProductDto productDto) {
        return productRepository.existsByBarcode(productDto.getBarcode());
    }
}
