package com.zmey.uptime.services;

import java.time.LocalDate;

// import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.zmey.uptime.repositories.TargetRepository;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.nodes.Tag;

import com.zmey.uptime.entities.Target;

@Service
public class TargetService {

    @Autowired
    private TargetRepository repository;

    public Target createTarget(Target target) {

        target.setCreatedAt(LocalDate.now());
        target.setUpdatedAt(LocalDate.now());

        return repository.save(target);
        
    }

    public Target updateTarget(Target target) {

        
        return repository.save(target);
    }

    // public List<Target> findAll(String customerId) {
        
    //     Long id = 0L;

    //     try {
    //         id = Long.parseLong(customerId); 
    //     } catch (Exception e){
    //         id = null;
    //     }

    //     Optional<List<Target>> result = repository.findAllByCustomerId(id);
    //     return result.get();

    // }
}
