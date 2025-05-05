package com.zmey.uptime.services;

import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;

import com.zmey.uptime.entities.Target;

import org.springframework.stereotype.Service;

import com.zmey.uptime.repositories.TargetRepository;

import org.springframework.beans.factory.annotation.Autowired;

import com.zmey.uptime.dto.CreateTargetDto;
import com.zmey.uptime.entities.Customer;
import com.zmey.uptime.repositories.CustomerRepository;

@Service
@Log4j2
public class TargetService {

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Target createTarget(CreateTargetDto targetDto) {

        Customer customer = customerRepository.findById(targetDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer_id not exist"));
        Target target = new Target();
        target.setCustomer(customer);
        target.setName(targetDto.getName());
        target.setUrl(targetDto.getUrl());
        target.setDescription(targetDto.getDescription());
        
        return targetRepository.save(target);
    }

    public void deleteTarget(Long id) {
        targetRepository.deleteById(id);
    }

    public Target updateTarget(Target target) {
        return targetRepository.save(target);
    }

    public Optional<Target> findById(Long id) {
        return targetRepository.findById(id);
    }

    public Optional<Target> findByUrl(String url) {
        return targetRepository.findByUrl(url);
    }

    public List<Target> findAll() {
        return targetRepository.findAll();
    }
}
