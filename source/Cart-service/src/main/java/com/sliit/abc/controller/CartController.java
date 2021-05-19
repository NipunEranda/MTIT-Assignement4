package com.sliit.abc.controller;

import com.sliit.abc.dto.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @PostMapping(consumes = "application/json",produces = "application/json")
    public @ResponseBody CartResponse createCart(@RequestBody CartRequest cartRequest){

        System.out.println("Cart details : "+ cartRequest);

        var cartResponse = new CartResponse();
        cartResponse.setCartId(UUID.randomUUID().toString());
        cartResponse.setMessage("Successfully created Shopping Cart");

        return cartResponse;
    }
    
}    
    