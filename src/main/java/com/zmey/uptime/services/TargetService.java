package com.zmey.uptime.services;

import com.zmey.uptime.dto.CreateTargetDto;
import com.zmey.uptime.entities.Customer;
import com.zmey.uptime.entities.Target;
import com.zmey.uptime.repositories.CustomerRepository;
import com.zmey.uptime.repositories.TargetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TargetService {

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public CreateTargetDto createTarget(CreateTargetDto targetDto) {

        Customer customer = customerRepository.findById(targetDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer_id not exist"));
        Target target = new Target();
        target.setCustomer(customer);
        target.setName(targetDto.getName());
        target.setUrl(targetDto.getUrl());
        target.setDescription(targetDto.getDescription());
        
        return toDto(targetRepository.save(target));
    }

    public void deleteTarget(Long id) {

        Optional<Target> existTarget = targetRepository.findById(id);

        if (existTarget.isPresent()) {
            targetRepository.deleteById(id);
        }
    }

    public Target updateTarget(Long id, Target target) {
        return targetRepository.findById(id)
                .map((Target existingTarget) -> {
                    existingTarget.setName(target.getName());
                    existingTarget.setCustomer(target.getCustomer());
                    existingTarget.setDescription(target.getDescription());
                    existingTarget.setUrl(target.getUrl());
                    log.info("existingTarget: " + existingTarget);
                    return targetRepository.save(existingTarget);
                }).orElseThrow(() -> new EntityNotFoundException("Target not found"));

    }

    public Optional<Target> findById(Long id) {
        return targetRepository.findById(id);
    }

    public Optional<Target> findByUrl(String url) {
        return targetRepository.findByUrl(url);
    }

    public List<CreateTargetDto> findAll() {

        List<Target> targets = targetRepository.findAll();

        List<CreateTargetDto> result = targets.stream()
                .map(element -> toDto(element))
                .collect(Collectors.toList());

        return result;
    }


    private CreateTargetDto toDto(Target target) {
        return new CreateTargetDto(target.getCustomer().getId(), target.getUrl(), target.getName(),
                target.getDescription());
    }

}
