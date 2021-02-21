package com.github.martynagil.drugstoremanagement.repositories;

import com.github.martynagil.drugstoremanagement.model.Address;
import com.github.martynagil.drugstoremanagement.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    Boolean existsByNameAndAddress_City(String name, String city);

}
