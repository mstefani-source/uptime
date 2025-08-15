package com.zmey.uptime.mappers;

import com.zmey.uptime.dto.TargetDto;
import com.zmey.uptime.entities.Customer;
import com.zmey.uptime.entities.Target;
import com.zmey.uptime.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TargetMapper {

    @Autowired
    private CustomerRepository customerRepository;

    public TargetDto mapModelToDto(Target model) {

        TargetDto target = new TargetDto();

        target.setDescription(model.getDescription());
        target.setName(model.getName());
        target.setActive(model.getActive());
        target.setUrl(model.getUrl());
        target.setCustomerId(model.getCustomer().getId());

        return target;
    }

    public Target mapDtoToModel (TargetDto targetDto) {

        Customer customer = customerRepository.findById(targetDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer_id not exist"));

        Target target = new Target();
        target.setCustomer(customer);
        target.setName(targetDto.getName());
        target.setUrl(targetDto.getUrl());
        target.setDescription(targetDto.getDescription());
        target.setActive(targetDto.getActive());

        return target;
    }
}
