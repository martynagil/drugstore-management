package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.service.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/shops")
    public void addShop(@RequestBody ShopDto shopDto) {
        shopService.addNewShop(shopDto);
    }
}
