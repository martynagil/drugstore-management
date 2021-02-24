package com.github.martynagil.drugstoremanagement.repositories;

import com.github.martynagil.drugstoremanagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    boolean existsByCityAndStreetAndNumberAndPostCode(String city, String streetAndNumber, String postCode);
}
