package com.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long policyId;

    private String policyName;

    @OneToOne
    @JoinColumn(name = "policyTypeId")
    private PolicyType policyType;

    private String policyCompany;
    private Integer tenure;
    private Double policyPrice;
    private Long coverage;
    @OneToOne
    @JoinColumn(name = "benefitId")
    private Benefit benefit;

    // Constructors, getters, setters, and other fields as needed
}