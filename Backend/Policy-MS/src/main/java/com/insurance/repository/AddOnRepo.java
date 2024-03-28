package com.insurance.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.AddOn;

@Repository
public interface AddOnRepo extends JpaRepository<AddOn, Long> {

    List<AddOn> findAllByPolicyTypePolicyTypeId(Long policyTypeId);
}
