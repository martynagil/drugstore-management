package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.controller.ShopDto;
import com.github.martynagil.drugstoremanagement.model.Address;
import com.github.martynagil.drugstoremanagement.model.Shop;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private ShopRepository repository;

    public ShopService(ShopRepository repository) {
        this.repository = repository;
    }

    public List<Shop> getAllShops() {
        return repository.findAll();
    }

    public void addNewShop(ShopDto shopDto) {
        Shop shop = createShopFromDto(shopDto);
        repository.save(shop);
    }

    private Shop createShopFromDto(ShopDto shopDto) {
        return new Shop(
                shopDto.getName(),
                new Address(
                        shopDto.getCity(),
                        shopDto.getPostCode(),
                        shopDto.getStreetAndNumber()
                )
        );
    }
}
