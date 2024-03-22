package com.insurance.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartResponse {

    private Long userId;
    private Long cartRequestId;
    private Policy policy;
    private Long price;
}