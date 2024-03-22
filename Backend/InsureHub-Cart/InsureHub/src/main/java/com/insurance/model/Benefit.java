package com.insurance.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Entity
public class Benefit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long benefitId;
    @Column(columnDefinition = "TEXT") // Use TEXT data type for JSON string
    private String benefitValue;

}
