package com.insurance.repository;

import com.insurance.entity.CartRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRequestRepo extends JpaRepository<CartRequest,Long> {

    List<CartRequest> findByUserId(Long userId);
    void deleteAllByUserId(@Param("userId") Long userId);



}
