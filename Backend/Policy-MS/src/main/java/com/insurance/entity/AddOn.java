package com.insurance.entity;



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
public class AddOn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long addOnId;

    @OneToOne
    @JoinColumn(name = "policyTypeId")
    private PolicyType policyType;

    private String addOnValue;

}
