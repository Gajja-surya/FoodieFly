package com.foodiefly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.foodiefly.entity.Restaurant;
import com.foodiefly.service.RestaurantService;

@Controller
public class RestaurantController {

	@Autowired
	RestaurantService rs;
	
	@GetMapping("/restaurants")
    public String getAllRestaurants(Model model) {
        List<Restaurant> restaurants = rs.getAllRestaurants();
        model.addAttribute("restaurants", restaurants); // Pass data to the template
        return "restaurant"; // Refers to restaurants.html in templates folder
    }
	
	
	@GetMapping("/api/restaurant")
    public List<Restaurant> getAllRestaurantsApi() {
        return rs.getAllRestaurants();
    }

    @GetMapping("/api/restaurant/{id}")
    public Restaurant getById(@PathVariable int id) {
        return rs.getById(id);
    }
	
    
    @GetMapping("/disp") // URL to access the page
    public String getAllRestaurant(Model model) {
        List<Restaurant> restaurants = rs.getAllRestaurants();
        model.addAttribute("restaurants", restaurants); // Pass data to the template
        return "restaurant"; // Refers to restaurants.html
    }
    
    
    
//	@GetMapping("/restaurant/{id}")
//	public Restaurant getById(@PathVariable int id) {
//		
//		return rs.getById(id);
//	}
//	
	@PostMapping("restaurant/insert")
	public String insertRestaurant(@RequestBody Restaurant restaurant) {
		
		return rs.insertRestaurant(restaurant);
	}
//	
//	
//	@PutMapping("/restaurant/update/{id}")
//	public String updateRestaurant(@PathVariable int id, @RequestBody Restaurant restaurant) {
//		
//		return rs.updateRestaurant(id, restaurant);
//	}
//	
//	@DeleteMapping("/restaurant/delete/{id}")
//	public String deleteRestaurant(@PathVariable int id) {
//		
//		return rs.deleteRestaurant(id);
//	}
}














