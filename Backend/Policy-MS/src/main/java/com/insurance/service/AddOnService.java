package com.insurance.service;


import java.util.List;

import com.insurance.contracts.AddOnDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.entity.AddOn;
import com.insurance.repository.AddOnRepo;

@Service
public class AddOnService implements AddOnDAO {

    @Autowired
    AddOnRepo dao;

    @Override
    public List<AddOn> getAddOnsFromAddOnIds(List<Long> addOnIds) {
        return dao.findAllById(addOnIds);
    }

    @Override
    public AddOn getAddOn(Long addOnId) {
        if (dao.findById(addOnId).isPresent())
            return dao.findById(addOnId).get();
        return null;
    }

    @Override
    public List<AddOn> getAddOnsFromPolicyTypeId(Long policyTypeId) {
//		return dao.findAll();
        return dao.findAllByPolicyTypePolicyTypeId(policyTypeId);
    }

}
