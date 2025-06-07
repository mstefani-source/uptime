/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.zmey.uptime.services;


import com.zmey.uptime.dto.CustomerDto;
import com.zmey.uptime.entities.Customer;
import com.zmey.uptime.mappers.CustomerMapper;
import com.zmey.uptime.repositories.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    public CustomerDto createCustomer(CustomerDto customerDto) {

        Customer customer = customerMapper.mapDtoToModel(customerDto);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.mapModelToDto(savedCustomer);
    }

    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers
                .stream()
                .map((customer) -> customerMapper.mapModelToDto(customer))
                .toList();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public void deleteTarget(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer_id not exist"));
        customer.setName(customerDto.getName());
        return customerMapper.mapModelToDto(customerRepository.save(customer));

    }
}
