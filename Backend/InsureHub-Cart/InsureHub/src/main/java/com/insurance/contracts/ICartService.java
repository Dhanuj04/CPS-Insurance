package com.insurance.contracts;

import com.insurance.model.CartRequestPrice;
import com.insurance.model.CartResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ICartService {

    String addToCart(Long policyId, CartRequestPrice req, Long userId);

    List<CartResponse> getAllItems(Long userId);

    void deleteFromCart(Long policyId);

    void deleteByCartId(Long cartId);

//    void deleteAllByUserId(Long userId);
    }