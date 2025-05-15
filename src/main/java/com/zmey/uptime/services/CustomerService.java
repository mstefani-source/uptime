/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.zmey.uptime.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zmey.uptime.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmey.uptime.entities.Customer;
import com.zmey.uptime.repositories.CustomerRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        return customerRepository.save(customer);
    }

    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers
                .stream()
                .map((customer) -> new CustomerDto(customer.getName()))
                .toList();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public void deleteTarget(Long id) {
        customerRepository.deleteById(id);
    }

}
