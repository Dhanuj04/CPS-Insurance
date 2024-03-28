package com.insurance.service;

import com.insurance.entity.AdminLogin;
import com.insurance.exception.RecordNotFoundException;
import com.insurance.repositary.AdminLoginRepositary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AdminLoginServicesTest {

    @InjectMocks
    AdminLoginService adminLoginServices;

    @Mock
    AdminLoginRepositary adminLoginRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail() {
        AdminLogin admin = new AdminLogin();
        admin.setEmail("test@admin.com");
        when(adminLoginRepository.findByEmail("test@admin.com")).thenReturn(admin);

        AdminLogin result = adminLoginServices.findByEmail("test@admin.com");
        assertEquals("test@admin.com", result.getEmail());
    }

    @Test
    public void testFindByEmail_NotFound() {
        when(adminLoginRepository.findByEmail("test@admin.com")).thenReturn(null);

        assertThrows(RecordNotFoundException.class, () -> {
            adminLoginServices.findByEmail("test@admin.com");
        });
    }
}