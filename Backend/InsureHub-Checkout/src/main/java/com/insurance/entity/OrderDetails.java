package com.insurance.entity;



import java.time.LocalDate;


import com.insurance.model.UserDetailsModel;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderDetails
{
    @Id
    @GeneratedValue
    private Long orderId;
    private Long userId;
    private LocalDate purchaseDate;
    @Column(columnDefinition = "longtext")
    private String policyAddOn;
    @OneToOne(cascade=CascadeType.ALL)
    private UserDetailsModel udm;
    private Double orderPrice;

}
