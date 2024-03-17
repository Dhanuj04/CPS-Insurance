package com.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insurance {
    private int insuranceCode;
    private String policyName;
    private String policyType;
    private int tenure;
    private double price;
    private int coverage;
    private String status;
    private String benefits;
}
