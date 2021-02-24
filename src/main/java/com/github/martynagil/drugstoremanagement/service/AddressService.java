package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.AddressDto;
import com.github.martynagil.drugstoremanagement.exceptions.AddressAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Address;
import com.github.martynagil.drugstoremanagement.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void addAddress(AddressDto addressDto) {
        if (addressExists(addressDto)) {
            throw new AddressAlreadyExistsException();
        }

        Address address = createAddressFromDto(addressDto);
        addressRepository.save(address);
    }

    private Address createAddressFromDto(AddressDto addressDto) {
        return new Address(
                addressDto.getCity(),
                addressDto.getPostCode(),
                addressDto.getStreetAndNumber()
        );
    }

    private boolean addressExists(AddressDto addressDto) {
        return addressRepository.existsByCityAndStreetAndNumberAndPostCode(
                addressDto.getCity(),
                addressDto.getStreetAndNumber(),
                addressDto.getPostCode()
        );
    }
}
