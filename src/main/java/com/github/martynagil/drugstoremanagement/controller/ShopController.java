package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShopController {

    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shops")
    public List<ShopDto> getShops() {
        return shopService.getAllShops().stream()
                .map(shop -> ShopDto.from(shop))
                .collect(Collectors.toList());
    }

    @GetMapping("/shops/{shopId}/employees")
    public List<EmployeeDto> getEmployees(@PathVariable Long shopId) {
        return shopService.getEmployees(shopId).stream()
                .map(employee -> EmployeeDto.from(employee))
                .collect(Collectors.toList());
    }

    @PostMapping("/shops")
    public void addShop(@RequestBody ShopDto shopDto) {
        shopService.addNewShop(shopDto);
    }
}
