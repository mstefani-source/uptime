package com.zmey.uptime.controllers;

// import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zmey.uptime.entities.Target;
import com.zmey.uptime.services.TargetService;

@RestController
@RequestMapping("/targets")
public class TargetController {

    @Autowired
    private TargetService targetService;

    // @GetMapping("/findall/{customerId}")
    // public List<Target> findAll(@PathVariable String customerId) {

    //     return targetService.findAll(customerId);
    // }

    @PostMapping()
    @ResponseBody
    public Target createTarget(@RequestBody Target target) {
        return targetService.createTarget(target);
    }
}
