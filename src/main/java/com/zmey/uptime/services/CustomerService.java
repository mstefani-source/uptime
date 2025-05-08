/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.zmey.uptime.services;


import java.util.List;
import java.util.Optional;

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

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public void deleteTarget(Long id) {
        customerRepository.deleteById(id);
    }

}
