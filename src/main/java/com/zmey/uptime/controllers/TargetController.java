package com.zmey.uptime.controllers;

import com.zmey.uptime.dto.TargetDto;
import com.zmey.uptime.entities.Target;
import com.zmey.uptime.mappers.TargetMapper;
// import com.zmey.uptime.services.JwtService;
import com.zmey.uptime.services.TargetService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/targets")
public class TargetController {
    private static final Logger logger = LogManager.getLogger(TargetController.class);

    @Autowired
    private TargetService targetService;

    // @Autowired
    // private JwtService jwtService;

    @Autowired
    private TargetMapper mapper;

    @PostMapping()
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public TargetDto createTarget(@RequestBody TargetDto target) {
        return targetService.createTarget(target);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeTarget(@PathVariable Long id) {

        targetService.deleteTarget(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public List<TargetDto> findAllTargets() {
        logger.info("find All Targets");
        return targetService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TargetDto> findTargetById(@PathVariable Long id) {

        Target target = targetService.findById(id).orElseThrow(() -> new EntityNotFoundException("Target not found"));
        return new ResponseEntity<>(mapper.mapModelToDto(target), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Target update(@PathVariable Long id, @RequestBody Target target) {
        return targetService.findById(id).map((Target existingTarget) -> {
            existingTarget.setName(target.getName());
            existingTarget.setCustomer(target.getCustomer());
            existingTarget.setDescription(target.getDescription());
            existingTarget.setUrl(target.getUrl());
            logger.info("existingTarget: " + existingTarget);
            return targetService.updateTarget(id, existingTarget);
        }).orElseThrow(() -> new EntityNotFoundException("Target not found"));

    }

}
