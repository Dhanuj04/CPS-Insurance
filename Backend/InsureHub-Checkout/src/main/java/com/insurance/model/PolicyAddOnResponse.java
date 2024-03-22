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
public class PolicyAddOnResponse
{
    private Long policyId;
    private Long price;
    private Policy policy;
}
