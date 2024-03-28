package com.insurance.contracts;


import java.util.List;

import com.insurance.entity.Policy;

public interface PolicyDAO {

    public List<Policy> findAllBypolicyTypeId(Long policyId);

    public List<Policy> findAllBypolicyIds(List<Long> policyIds);

    public String updatePolicy(Policy policy);

    public String deletePolicy(Long policyId);

    public Policy loadPolicy(Long insuranceId);


}
