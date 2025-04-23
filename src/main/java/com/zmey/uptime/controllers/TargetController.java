package com.zmey.uptime.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zmey.uptime.entities.Target;
import com.zmey.uptime.services.TargetService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/targets")
public class TargetController {
    private static final Logger logger = LogManager.getLogger(TargetController.class);

    @Autowired
    private TargetService targetService;

    @PostMapping()
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public Target createTarget(@RequestBody Target target)
    {   
        logger.info("target " + target);
        return targetService.createTarget(target);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeTarget(@PathVariable Long id) {
        Optional<Target> existTarget = targetService.findById(id);

        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        if (existTarget.isPresent()) {
            targetService.deleteTarget(id);
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;

    }

    @GetMapping()
    public List<Target> findAllTargets() {

        return targetService.findAll();
    }

    @GetMapping("/{id}")
    public Target findTargetById(@PathVariable Long id) {

        return targetService.findById(id).orElseThrow(() -> new EntityNotFoundException("Target not found"));
    }

    
    @PutMapping("/{id}")
    public Target update(@PathVariable Long id, @RequestBody Target target) {
        return targetService.findById(id).map((Target existingTarget) -> {
            existingTarget.setName(target.getName());
            existingTarget.setCustomer(target.getCustomer());
            existingTarget.setDescription(target.getDescription());
            existingTarget.setUrl(target.getUrl());
            logger.info("existingTarget: " + existingTarget);
            return targetService.updateTarget(existingTarget);
        }).orElseThrow(() -> new EntityNotFoundException("Target not found"));

    }

}
