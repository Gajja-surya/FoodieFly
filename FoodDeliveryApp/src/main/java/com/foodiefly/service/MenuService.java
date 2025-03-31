package com.foodiefly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodiefly.entity.Menu;
import com.foodiefly.entity.Restaurant;
import com.foodiefly.repository.MenuRepository;

@Service
public class MenuService {

	
	
	
	 @Autowired
	    private MenuRepository menuRepository;

	    // Create a new menu item
	    public Menu createMenu(Menu menu) {
	        return menuRepository.save(menu);
	    }

	    // Get all menu items
	    public List<Menu> getAllMenus() {
	        return menuRepository.findAll();
	    }

	    // Get a menu item by ID
	    public Optional<Menu> getMenuById(int menuId) {
	        return menuRepository.findById(menuId);
	    }

	    // Get menu items by restaurant
	    public List<Menu> getMenusByRestaurant(Restaurant restaurant) {
	        return menuRepository.findByRestaurant(restaurant);
	    }

	    // Get menu items by restaurant ID
	    public List<Menu> getMenusByRestaurantId(int restaurantId) {
	        return menuRepository.findByRestaurantRestaurantId(restaurantId);
	    }

	    // Get menu items by item name
	    public List<Menu> getMenusByItemName(String itemName) {
	        return menuRepository.findByItemName(itemName);
	    }

	    // Get available menu items
	    public List<Menu> getAvailableMenus() {
	        return menuRepository.findByIsAvailable(true);
	    }

	    // Get menu items by price range
	    public List<Menu> getMenusByPriceRange(int minPrice, int maxPrice) {
	        return menuRepository.findByPriceBetween(minPrice, maxPrice);
	    }

	    // Get menu items with rating above a threshold
	    public List<Menu> getMenusByMinimumRating(float rating) {
	        return menuRepository.findByRatingGreaterThanEqual(rating);
	    }

	    // Update a menu item
	    public Menu updateMenu(int menuId, Menu updatedMenu) {
	        Optional<Menu> existingMenuOpt = menuRepository.findById(menuId);
	        if (existingMenuOpt.isPresent()) {
	            Menu existingMenu = existingMenuOpt.get();
	            existingMenu.setItemName(updatedMenu.getItemName());
	            existingMenu.setDescription(updatedMenu.getDescription());
	            existingMenu.setPrice(updatedMenu.getPrice());
	            existingMenu.setRating(updatedMenu.getRating());
	            existingMenu.setAvailable(updatedMenu.isAvailable());
	            existingMenu.setImagePath(updatedMenu.getImagePath());
	            existingMenu.setRestaurant(updatedMenu.getRestaurant());
	            return menuRepository.save(existingMenu);
	        } else {
	            throw new RuntimeException("Menu item with ID " + menuId + " not found");
	        }
	    }

	    // Delete a menu item
	    public void deleteMenu(int menuId) {
	        if (menuRepository.existsById(menuId)) {
	            menuRepository.deleteById(menuId);
	        } else {
	            throw new RuntimeException("Menu item with ID " + menuId + " not found");
	        }
	    }
}
