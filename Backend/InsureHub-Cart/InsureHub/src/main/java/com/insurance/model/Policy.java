package com.insurance.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Policy {

    private Long policyId;

    private String policyName;

    private PolicyType policyType;

    private String policyCompany;
    private Integer tenure;
    private Double policyPrice;
    private Long coverage;

    private Benefit benefit;

}