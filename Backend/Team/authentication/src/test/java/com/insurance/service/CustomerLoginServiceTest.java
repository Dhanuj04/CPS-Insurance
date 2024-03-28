package com.insurance.service;


import com.insurance.entity.CustomerLogin;
import com.insurance.exception.RecordNotFoundException;
import com.insurance.repositary.CustomerLoginRepositary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CustomerLoginServiceTest {

    @InjectMocks
    CustomerLoginService customerLoginServices;

    @Mock
    CustomerLoginRepositary customerLoginRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail() {
        CustomerLogin customer = new CustomerLogin();
        customer.setEmail("test@customer.com");
        when(customerLoginRepository.findByEmail("test@customer.com")).thenReturn(customer);

        CustomerLogin result = customerLoginServices.findByEmail("test@customer.com");
        assertEquals("test@customer.com", result.getEmail());
    }

    @Test
    public void testFindByEmail_NotFound() {
        when(customerLoginRepository.findByEmail("test@customer.com")).thenReturn(null);

        assertThrows(RecordNotFoundException.class, () -> {
            customerLoginServices.findByEmail("test@customer.com");
        });
    }
}