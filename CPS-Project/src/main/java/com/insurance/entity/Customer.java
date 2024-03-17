package com.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int userId;
    private String name;
    private String email;
    private String password;
    private LocalDate dob;
    private String city;
    private String state;
    private double Credits;
}
