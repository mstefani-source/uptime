package com.zmey.uptime.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zmey.uptime.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    Optional<Customer> findById(Long id);
}
