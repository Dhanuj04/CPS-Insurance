package com.insurance.service;

import com.insurance.entity.CustomerLogin;
import com.insurance.exception.RecordNotFoundException;
import com.insurance.repositary.CustomerLoginRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerLoginService {


    @Autowired
    private CustomerLoginRepositary customerLoginRepositary;
    public CustomerLogin findByEmail(String email) {
        CustomerLogin customer = customerLoginRepositary.findByEmail(email);
        if(customer == null) {
            throw new RecordNotFoundException("Customer not found with email: " + email);
        }
        return customer;
    }

}
