package com.insurance.controller;


import com.insurance.entity.CustomerLogin;
import com.insurance.entity.LoginRequest;
import com.insurance.service.CustomerLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerLoginController {
    private final CustomerLoginService customerLoginService;

    public CustomerLoginController(CustomerLoginService customerLoginServices) {
        this.customerLoginService = customerLoginServices;
    }

    @PostMapping("/customer/login")
    public ResponseEntity<String> customerLogin(@RequestBody LoginRequest loginRequest) {
        CustomerLogin customer = customerLoginService.findByEmail(loginRequest.getEmail());
        if (customer.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok("Customer login successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid password");
        }
    }
}

