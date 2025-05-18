package com.zmey.uptime.controllers;

import java.util.List;
import java.util.Optional;

import com.zmey.uptime.dto.CustomerDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zmey.uptime.entities.Customer;
import com.zmey.uptime.services.CustomerService;

@Log4j2
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody CustomerDto customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping()
    public List<CustomerDto> findAllCustomers() {
        return customerService.findAllCustomers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCustomer(@PathVariable Long id) {

        Optional<Customer> existTarget = customerService.findById(id);
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        if (existTarget.isPresent()) {
            customerService.deleteTarget(id);
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customer) {
        return customerService.updateCustomer(id, customer);
    }
}
