package com.insurance.entity;

import lombok.Data;
import lombok.Setter;


@Data
public class Order {
    @Setter
    private int orderID;
    @Setter
    private int userID;
    @Setter
    private int policyID;
    @Setter
    private String policyName;
    @Setter
    private String policyType;
    @Setter
    private int tenure;
    @Setter
    private double policyPrice;
    @Setter
    private int coverage;
    @Setter
    private String status;
}
