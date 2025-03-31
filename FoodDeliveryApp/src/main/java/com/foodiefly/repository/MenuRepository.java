package com.foodiefly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodiefly.entity.Menu;
import com.foodiefly.entity.Restaurant;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

	
	
	 // Find all menu items by restaurant
    List<Menu> findByRestaurant(Restaurant restaurant);

    // Find menu items by item name (case-sensitive)
    List<Menu> findByItemName(String itemName);

    // Find menu items by availability
    List<Menu> findByIsAvailable(boolean isAvailable);

    // Find menu items by price range
    List<Menu> findByPriceBetween(int minPrice, int maxPrice);

    // Find menu items by rating greater than or equal to a value
    List<Menu> findByRatingGreaterThanEqual(float rating);

    // Find menu items by restaurant ID
    List<Menu> findByRestaurantRestaurantId(int restaurantId);
}
