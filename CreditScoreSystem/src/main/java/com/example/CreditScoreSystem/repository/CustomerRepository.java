package com.example.CreditScoreSystem.repository;

import com.example.CreditScoreSystem.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
