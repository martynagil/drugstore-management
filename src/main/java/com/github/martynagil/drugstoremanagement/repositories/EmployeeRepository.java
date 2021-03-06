package com.github.martynagil.drugstoremanagement.repositories;

import com.github.martynagil.drugstoremanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByDateOfDismissalIsNullAndShopId(Long shopId);
}
