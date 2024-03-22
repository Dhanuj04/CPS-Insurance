package com.insurance.contracts;


import com.insurance.entity.OrderDetails;

import java.util.List;



public interface IOrderDAO
{
    public List<OrderDetails> loadAllOrders();
}
