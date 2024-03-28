package com.insurance.repositary;

import com.insurance.entity.AdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLoginRepositary extends JpaRepository<AdminLogin,String> {
    AdminLogin findByEmail(String email);

}
