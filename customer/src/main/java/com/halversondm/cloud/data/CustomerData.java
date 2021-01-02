package com.halversondm.cloud.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerData extends CrudRepository<Customer, Integer> {

    Optional<Customer> findCustomerByCustomerId(Integer customerId);

    @Query("from Customer")
    List<Customer> findCustomers();

}
