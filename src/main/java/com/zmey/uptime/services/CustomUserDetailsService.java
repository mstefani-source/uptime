package com.zmey.uptime.services;

import com.zmey.uptime.mappers.CustomerMapper;
import com.zmey.uptime.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerMapper.mapModelToDto(customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("no user with login %s is registered", email)
                )));
    }
}
