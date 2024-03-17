package com.insurance.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Policy {

    private int insuranceCode;
    private String policyName;
    private String policyType;
    private int tenure;
    private double price;
    private int coverage;
    private LocalDate startDate;
    private LocalDate endDate;
}
