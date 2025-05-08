package com.zmey.uptime.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zmey.uptime.entities.Customer;
import com.zmey.uptime.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {

        return customerService.createCustomer(customer);
    }

    @GetMapping()
    public List<Customer> findAllCustomers() {

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
}
