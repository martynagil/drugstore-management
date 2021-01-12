package com.github.martynagil.drugstoremanagement.repositories;

import com.github.martynagil.drugstoremanagement.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
