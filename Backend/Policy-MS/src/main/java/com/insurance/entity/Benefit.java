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
public class Benefit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long benefitId;
    @Column(columnDefinition = "TEXT")
    private String benefitValue;

}