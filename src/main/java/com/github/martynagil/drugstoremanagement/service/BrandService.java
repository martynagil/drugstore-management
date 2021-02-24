package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.BrandDto;
import com.github.martynagil.drugstoremanagement.exceptions.BrandAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Brand;
import com.github.martynagil.drugstoremanagement.repositories.BrandRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProducerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class BrandService {

    private BrandRepository brandRepository;
    private ProducerRepository producerRepository;

    public void addBrand(BrandDto brandDto) {
        if (brandExists(brandDto)) {
            throw new BrandAlreadyExistsException();
        }

        Brand brand = createBrandFromDto(brandDto);
        brandRepository.save(brand);
    }

    private Brand createBrandFromDto(BrandDto brandDto) {
        return new Brand(
                brandDto.getName(),
                brandDto.getEmail(),
                brandDto.getTelephone(),
                producerRepository.findById(brandDto.getProducerId())
                        .orElseThrow(EntityNotFoundException::new)
        );
    }

    private boolean brandExists(BrandDto brandDto) {
        return brandRepository.existsByNameAndProducerId(
                brandDto.getName(),
                brandDto.getProducerId()
        );
    }
}
