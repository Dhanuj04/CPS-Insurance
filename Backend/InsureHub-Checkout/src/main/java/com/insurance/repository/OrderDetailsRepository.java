package com.insurance.repository;


import java.util.List;

import com.insurance.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>
{
    public List<OrderDetails> findByuserId(Long userId);
}
