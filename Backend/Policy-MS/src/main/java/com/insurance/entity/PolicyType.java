package com.insurance.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PolicyType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long policyTypeId;

    private String policyTypeValue;

}