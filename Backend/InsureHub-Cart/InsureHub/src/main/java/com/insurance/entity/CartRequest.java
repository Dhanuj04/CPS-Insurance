package com.insurance.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "CartRequest")
public class CartRequest {

    @Id
    @GeneratedValue
    @Column(name = "cart_request_id")
    private Long cartRequestId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "policy_id")
    private Long policyId;

    private Long price;

}