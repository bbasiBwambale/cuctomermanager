package com.ford.customermanager.controller;

import com.ford.customermanager.domain.Customer;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CustomerController {
    ResponseEntity findAllCustomers();
    ResponseEntity findCustomerById(UUID customerId);

    ResponseEntity createCustomer(Customer customer);
    ResponseEntity updateCustomer(UUID customerId, Customer customer);

    void deleteCustomer(UUID customerId);
}
