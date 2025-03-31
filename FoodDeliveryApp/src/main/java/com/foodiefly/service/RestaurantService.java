package com.foodiefly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodiefly.entity.Restaurant;
import com.foodiefly.repository.RestaurantRepository;


@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getById(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public String insertRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return "Restaurant inserted successfully";
    }

    public String updateRestaurant(int id, Restaurant restaurant) {
        Restaurant existing = restaurantRepository.findById(id).orElse(null);
        if (existing != null) {
            restaurant.setRestaurantId(id);
            restaurantRepository.save(restaurant);
            return "Restaurant updated successfully";
        }
        return "Restaurant not found";
    }

    public String deleteRestaurant(int id) {
        restaurantRepository.deleteById(id);
        return "Restaurant deleted successfully";
    }
}
//@Service
//public class RestaurantService {
//
//	@Autowired
//	RestaurantRepository repo;
//	
//	
//	public List<Restaurant> getAllRestaurants() {
//		
//		return (List<Restaurant>)repo.findAll();
//	}
//	
//	public Restaurant getById(int id) {
//		
//		return repo.findById(id);
//	}
//	
//	public String insertRestaurant(Restaurant restaurant) {
//		
//		Restaurant save = repo.save(restaurant);
//		return "Restaurant Details are Inserted";
//	}
//	
//	public String updateRestaurant(int id, Restaurant restaurant) {
//		
//		if(repo.findById(id) !=null) {
//			repo.save(restaurant);
//		}
//		return "Restaurant Details are Updated";
//	}
//	
//	public String deleteRestaurant(int id) {
//		
//		repo.delete(repo.findById(id));
//		return "Restaurant Details are Deleted";
//	}
//	
//}











