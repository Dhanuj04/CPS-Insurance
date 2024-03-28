package com.insurance.repositary;

import com.insurance.entity.CustomerLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerLoginRepositary extends JpaRepository<CustomerLogin,String> {
    CustomerLogin findByEmail(String email);
}
