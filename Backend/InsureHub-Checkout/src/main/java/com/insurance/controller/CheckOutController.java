package com.insurance.controller;


import java.io.IOException;
import java.util.List;

import com.insurance.entity.OrderDetails;
import com.insurance.model.OrderDetailsModel;
import com.insurance.model.OrderDetailsResponse;
import com.insurance.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;



@RestController
@RequestMapping("/order")
@CrossOrigin(origins = { "*" })
public class CheckOutController
{


    @Autowired
    OrderService service;
    @PostMapping("/addOrderWithUserDetails")
    public ResponseEntity addOrderWithUserDetails(@RequestBody OrderDetailsModel orderDetailsModel) throws JsonProcessingException
    {
        OrderDetails od=service.addOrder(orderDetailsModel);

        return new ResponseEntity("Order Added successfully",HttpStatus.CREATED);
    }

    @GetMapping("/loadAllOrders")
    public ResponseEntity loadAllOrders() throws IOException
    {
        List<OrderDetailsResponse> odl=service.loadAllOrders();
        return new ResponseEntity(odl,HttpStatus.OK);
    }
    @GetMapping("/getOrderByUserId/{userId}")
    public ResponseEntity getOrderByUserId(@PathVariable Long userId) throws JsonMappingException, JsonProcessingException
    {
        List<OrderDetailsResponse> odl=service.loadOrdersByUserId(userId);
        return new ResponseEntity(odl,HttpStatus.OK);
    }
}