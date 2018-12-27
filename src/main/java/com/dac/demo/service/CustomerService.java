package com.dac.demo.service;

import com.dac.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();

    Customer save(Customer customer);

    Optional<Customer> findById(Integer id);
}
