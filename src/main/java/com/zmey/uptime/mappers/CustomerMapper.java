package com.zmey.uptime.mappers;

import com.zmey.uptime.dto.CustomerDto;
import com.zmey.uptime.entities.Customer;
import com.zmey.uptime.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDto mapModelToDto(Customer model) {

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(model.getId());
        customerDto.setName(model.getName());
        customerDto.setEmail(model.getEmail());
        customerDto.setPassword(model.getPassword());
        customerDto.setRole(model.getRole());

        return customerDto;
    }

    public Customer mapDtoToModel (CustomerDto customerDto) {

        Customer customer = new Customer();

        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());

        return customer;
    }
}
