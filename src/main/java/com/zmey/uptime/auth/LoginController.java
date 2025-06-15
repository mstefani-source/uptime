package com.zmey.uptime.auth;

import com.zmey.uptime.dto.CustomerDto;
import com.zmey.uptime.services.CustomUserDetailsService;
import com.zmey.uptime.services.CustomerService;
import com.zmey.uptime.services.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Аутентификация")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

//    @Autowired
//    private CustomerService service;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/login")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public String example() {
        return "Hello, world!!";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        log.info("Token Request");
        CustomerDto customer = (CustomerDto) customUserDetailsService.loadUserByUsername(request.getEmail());

        String token = jwtService.generateToken(customer);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/admin")
    @Operation(summary = "Доступен только авторизованным пользователям с ролью ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }

//    @GetMapping("/get-admin")
//    @Operation(summary = "Получить роль ADMIN (для демонстрации)")
//    public void getAdmin() {
//        service.getAdmin();
//    }
}