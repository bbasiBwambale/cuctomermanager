package com.ford.customermanager.service;

import com.ford.customermanager.domain.Customer;
import com.ford.customermanager.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    CustomerServiceImpl customerService;

    Customer customer;
    Customer updateCustomer;

    List<Customer> customerList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        customer = Customer.builder().customerId(UUID.randomUUID()).customerName("abc").build();
        updateCustomer = Customer.builder().customerId(UUID.randomUUID()).customerName("updatedCustomer").build();
        customerList.add(customer);
        customerList.add(updateCustomer);
    }

    @Test
    void findAllCustomers() {
        Mockito.when(customerRepository.findAll()).thenReturn(customerList);
        Assertions.assertEquals(customerList, customerService.findAllCustomers());
    }

    @Test
    void findCustomerById() {
        Mockito.when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.ofNullable(customer));
        Customer customer1 = customerService.findCustomerById(customer.getCustomerId());
        Assertions.assertEquals(customer, customer1);
    }

    @Test
    void createCustomer() {
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Customer customer1 = customerService.createCustomer(customer);
        Assertions.assertEquals(customer, customer1);
    }

    @Test
    void updateCustomer() {
        Mockito.when(customerRepository.save(customer)).thenReturn(updateCustomer);
        Customer customer1 = customerService.updateCustomer(customer.getCustomerId(),customer);
        Assertions.assertEquals(updateCustomer, customer1);
    }

    @Test
    void deleteCustomer() {
        customerService.deleteCustomer(customer.getCustomerId());
        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(customer.getCustomerId());
    }
}