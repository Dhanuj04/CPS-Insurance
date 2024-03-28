package com.insurance.service;


import java.util.List;
import java.util.Optional;

import com.insurance.contracts.PolicyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.entity.Policy;
import com.insurance.repository.PolicyRepo;

@Service
public class PolicyDAOService implements PolicyDAO {

    @Autowired
    private PolicyRepo dao;

    @Override
    public Policy loadPolicy(Long insuranceId) {
        Optional<Policy> hi = dao.findById(insuranceId);
        if (hi.isPresent())
            return hi.get();
        return null;
    }

    @Override
    public List<Policy> findAllBypolicyTypeId(Long policyId) {
        return dao.findAllByPolicyTypePolicyTypeId(policyId);
    }

    @Override
    public String updatePolicy(Policy updatedPolicy) {
        Long policyId = updatedPolicy.getPolicyId();
        Optional<Policy> existingPolicy = dao.findById(policyId);

        if (existingPolicy.isPresent()) {
            Policy policyToUpdate = existingPolicy.get();
            policyToUpdate.setPolicyName(updatedPolicy.getPolicyName());
            policyToUpdate.setPolicyType(updatedPolicy.getPolicyType());
            policyToUpdate.setPolicyCompany(updatedPolicy.getPolicyCompany());
            policyToUpdate.setTenure(updatedPolicy.getTenure());
            policyToUpdate.setPolicyPrice(updatedPolicy.getPolicyPrice());
            policyToUpdate.setCoverage(updatedPolicy.getCoverage());
            policyToUpdate.setBenefit(updatedPolicy.getBenefit());

            dao.save(policyToUpdate);

            return "Policy updated successfully";
        }
        return "Policy not found, update failed";

    }

    @Override
    public String deletePolicy(Long policyId) {
        dao.deleteById(policyId);
        return "element deleted";
    }

    @Override
    public List<Policy> findAllBypolicyIds(List<Long> policyIds) {
        return dao.findAllById(policyIds);
    }

}
