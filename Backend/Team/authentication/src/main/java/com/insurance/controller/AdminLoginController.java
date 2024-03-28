package com.insurance.controller;


import com.insurance.entity.AdminLogin;
import com.insurance.entity.LoginRequest;
import com.insurance.service.AdminLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminLoginController {
    private final AdminLoginService adminLoginService;

    public AdminLoginController(AdminLoginService adminService) {
        this.adminLoginService = adminService;
    }

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "server is running .... ";
    }

    @PostMapping("/admin/login")
    public ResponseEntity<String> adminLogin(@RequestBody LoginRequest loginRequest) {
        AdminLogin admin = adminLoginService.findByEmail(loginRequest.getEmail());
        if (admin.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok("Admin login successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid password");
        }
    }
}
