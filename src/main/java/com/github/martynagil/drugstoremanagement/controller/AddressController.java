package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.dto.AddressDto;
import com.github.martynagil.drugstoremanagement.service.AddressService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("addresses")
public class AddressController {

    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public void addAddress(@RequestBody AddressDto addressDto) {
        addressService.addAddress(addressDto);
    }
}
