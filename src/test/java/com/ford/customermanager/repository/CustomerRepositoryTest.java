package com.ford.customermanager.repository;

import com.ford.customermanager.domain.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

@DataJpaTest
class CustomerRepositoryTest {
    Customer customer;

    @BeforeEach
    void setUp(){
        customer = Customer.builder().customerId(UUID.randomUUID()).customerName("abc").build();
    }

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void findAllCustomers() {
        Assertions.assertEquals(4, customerRepository.findAll().size());
    }

    @Test
    void findCustomerById() {
    Customer cus = customerRepository.save(customer);
    Assertions.assertEquals(cus, customerRepository.findById(customer.getCustomerId()).get());
    }

    @Test
    void createCustomer() {
        customerRepository.save(customer);
        Assertions.assertEquals(5, customerRepository.findAll().size());
    }

    @Test
    void updateCustomer() {
        customerRepository.save(customer);
        Assertions.assertEquals(5, customerRepository.findAll().size());
        customer.setCustomerName("UpdatedName");
        customerRepository.save(customer);
        Assertions.assertEquals(5, customerRepository.findAll().size());
    }

    @Test
    void deleteCustomer() {
        customerRepository.save(customer);
        Assertions.assertEquals(5, customerRepository.findAll().size());
        customerRepository.delete(customer);
        Assertions.assertEquals(4, customerRepository.findAll().size());
    }

}