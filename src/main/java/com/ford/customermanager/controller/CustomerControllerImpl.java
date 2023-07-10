package com.ford.customermanager.controller;

import com.ford.customermanager.domain.Customer;
import com.ford.customermanager.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CustomerControllerImpl implements CustomerController{

    private CustomerService customerService;

    public CustomerControllerImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @GetMapping("/customers")
    public ResponseEntity findAllCustomers() {
        return new ResponseEntity(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/customers/{customerId}")
    public ResponseEntity findCustomerById(@PathVariable UUID customerId) {
        return new ResponseEntity(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

    @Override
    @PostMapping("/customers")
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/customers/{customerId}")
    public ResponseEntity updateCustomer(@PathVariable UUID customerId, @RequestBody Customer customer) {
       return new ResponseEntity(customerService.updateCustomer(customerId, customer), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable UUID customerId) {
        customerService.deleteCustomer(customerId);
    }
}
