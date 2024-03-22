package com.insurance.service;



import java.util.List;

import com.insurance.contracts.IOrderDAO;
import com.insurance.entity.OrderDetails;
import com.insurance.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderDAOImpl implements IOrderDAO
{
    @Autowired
    OrderDetailsRepository orderRepo;

    @Override
    public List<OrderDetails> loadAllOrders()
    {
        return orderRepo.findAll();
    }


}