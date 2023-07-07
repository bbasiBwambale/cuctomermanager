package com.ford.customermanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ford.customermanager.domain.Customer;
import com.ford.customermanager.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebMvcTest
class CustomerControllerImplTest {
    @MockBean
    CustomerServiceImpl customerService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    Customer customer;
    Customer updatedCustomer;
    @BeforeEach
    void setUp() {
        customer = Customer.builder().customerId(UUID.randomUUID()).customerName("abc").build();
        updatedCustomer = Customer.builder().customerId(UUID.randomUUID()).customerName("updatedCustomer").build();
    }

    @Test
    void findAllCustomers() throws Exception {
        List<Customer> customerList = new ArrayList<>(List.of(new Customer[]{
                customer, updatedCustomer
        }));

        Mockito.when(customerService.findAllCustomers()).thenReturn(customerList);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)

                )
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customerList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void findCustomerById() throws Exception{
        Mockito.when(customerService.findCustomerById(customer.getCustomerId())).thenReturn(customer);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/customers/"+ customer.getCustomerId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customer)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void createCustomer() throws Exception {
        Mockito.when(customerService.createCustomer(customer)).thenReturn(customer);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer))
        )
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customer)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    void updateCustomer() throws Exception {
        Mockito.when(customerService.updateCustomer(customer.getCustomerId(),customer)).thenReturn(updatedCustomer);
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/customers/"+customer.getCustomerId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(customer))
                )
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(updatedCustomer)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }


    @Test
    void deleteCustomer() throws Exception{
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/customers/"+customer.getCustomerId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}