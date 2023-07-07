package com.ford.customermanager.service;

import com.ford.customermanager.domain.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CustomerService {


    List<Customer> findAllCustomers();
    Customer findCustomerById(UUID customerId);

    Customer createCustomer(Customer customer);
    Customer updateCustomer(UUID customerId, Customer customer);

    void deleteCustomer(UUID customerId);
}
