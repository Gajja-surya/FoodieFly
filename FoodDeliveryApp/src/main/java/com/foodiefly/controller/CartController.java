package com.foodiefly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
	public class CartController {

	    @GetMapping("/cart")
	    public String showCart() {
	        return "cart"; // Maps to cart.html
	    }
	}
