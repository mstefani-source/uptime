package com.zmey.uptime.controllers;

import java.util.List;
import java.util.Optional;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zmey.uptime.entities.Target;
import com.zmey.uptime.services.TargetService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/targets")
public class TargetController {

    @Autowired
    private TargetService targetService;

    @PostMapping()
    @ResponseBody
    public Target createTarget(@RequestBody Target target) {
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

        
    @PutMapping
    public Target update(@RequestBody Target target) {

        

        Target target = targetService.findById(id).orElseThrow(() -> new EntityNotFoundException("Target not found"));

        return targetService.updateTarget(target);
    }

}
