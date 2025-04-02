package com.zmey.uptime.services;

import java.util.List;
import java.util.Optional;

// import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.zmey.uptime.repositories.TargetRepository;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import com.zmey.uptime.entities.Target;

@Service
@Log4j2
public class TargetService {

    @Autowired
    private TargetRepository repository;

    public Target createTarget(Target target) {

        return repository.save(target);
        
    }

    public void deleteTarget(Long id) {
        log.info("deleting target " + id );

        repository.deleteById(id);

    }

    public Target updateTarget(Target target) {
       
        return repository.save(target);
    }


    public Optional<Target> findById (Long id) {

        return repository.findById(id);
    }

    public List<Target> findAll() {
        return repository.findAll();
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
