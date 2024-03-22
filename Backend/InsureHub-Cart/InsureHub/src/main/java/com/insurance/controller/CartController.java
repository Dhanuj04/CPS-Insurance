package com.insurance.controller;



import java.util.List;



import com.insurance.model.CartRequestPrice;
import com.insurance.model.CartResponse;
import com.insurance.repository.CartRequestRepo;
import com.insurance.service.CartService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shoppingcart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService service;

    @Autowired
    private CartRequestRepo repo;


    @GetMapping("/{userId}/buyCart")
    public ResponseEntity<List<CartResponse>> addAllRequests(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(service.getAllItems(userId), HttpStatus.CREATED);
    }

//    Working
    @PostMapping("/{userId}/addToCart/{policyid}")
    public ResponseEntity<String> addToCart(@PathVariable("userId") Long userId,
                                            @PathVariable("policyid") Long policyid, @RequestBody CartRequestPrice req) {
        service.addToCart(policyid, req,userId);
        return new ResponseEntity<>("added to cart", HttpStatus.OK);
    }


//Working
    @DeleteMapping("/{userId}/deleteItemFromCart/{policyid}")
    public ResponseEntity<String> deleteFromCart(@PathVariable("userId") Long userId,
                                                 @PathVariable("policyid") Long policyid) {
        service.deleteFromCart(policyid);
        return new ResponseEntity<>("deleted from cart", HttpStatus.OK);
    }

    //Working
    @DeleteMapping("/deleteItemFromCart/{cartRequestId}")
    public void deleteFromCartById(@PathVariable Long cartRequestId)
    {
        service.deleteByCartId(cartRequestId);

    }

    // Working
    @DeleteMapping("/deleteByUserId/{userId}")
    @Transactional
    public void deleteByUserId(@PathVariable Long userId)
    {
        repo.deleteAllByUserId(userId);
    }

    @GetMapping("/getTotalPrice/{userId}")
    public Long calculateTotalPrice(@PathVariable("userId") Long userId)
    {
        List<CartResponse> response=service.getAllItems(userId);
        Long total=0L;
        for(CartResponse r:response)
        {
            total+=r.getPrice();
        }
        return total;

    }
}