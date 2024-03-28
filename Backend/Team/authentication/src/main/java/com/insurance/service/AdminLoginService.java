package com.insurance.service;

import com.insurance.entity.AdminLogin;
import com.insurance.exception.RecordNotFoundException;
import com.insurance.repositary.AdminLoginRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {

    @Autowired
    private AdminLoginRepositary adminLoginRepository;

    public AdminLogin findByEmail(String email) {
        AdminLogin admin = adminLoginRepository.findByEmail(email);
        if(admin == null) {
            throw new RecordNotFoundException("Admin not found with email: " + email);
        }
        return admin;
    }
}
