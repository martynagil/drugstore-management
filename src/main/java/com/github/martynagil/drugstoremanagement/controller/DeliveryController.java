package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.dto.DeliveryDto;
import com.github.martynagil.drugstoremanagement.service.DeliveryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deliveries")
public class DeliveryController {

    private DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public void createDelivery(@RequestBody DeliveryDto deliveryDto) {
        deliveryService.createDelivery(deliveryDto);
    }
}
