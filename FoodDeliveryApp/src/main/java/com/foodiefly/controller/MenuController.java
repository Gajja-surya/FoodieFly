package com.foodiefly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodiefly.entity.Menu;
import com.foodiefly.entity.Restaurant;
import com.foodiefly.service.MenuService;
import com.foodiefly.service.RestaurantService;

@Controller
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    // Display all menu items
    @GetMapping
    public String getAllMenus(Model model) {
        List<Menu> menus = menuService.getAllMenus();
        model.addAttribute("menus", menus);
        return "menu"; // Refers to menu.html in templates/
    }

    // Display a single menu item by ID
    @GetMapping("/{menuId}")
    public String getMenuById(@PathVariable int menuId, Model model) {
        Optional<Menu> menu = menuService.getMenuById(menuId);
        if (menu.isPresent()) {
            model.addAttribute("menu", menu.get());
            return "menu-detail"; // Assuming a separate detail page, adjust as needed
        } else {
            return "error";
        }
    }

    // Display menu items by restaurant ID
    @GetMapping("/restaurant/{restaurantId}")
    public String getMenusByRestaurantId(@PathVariable int restaurantId, Model model) {
        List<Menu> menus = menuService.getMenusByRestaurantId(restaurantId);
        model.addAttribute("menus", menus);
        Restaurant restaurant = restaurantService.getById(restaurantId);
        model.addAttribute("restaurantName", restaurant.getName());
        return "menu"; // Refers to menu.html
    }

    // Display form to create a new menu item
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("menu", new Menu());
        return "menu-form"; // Ensure you have this template
    }

    // Handle form submission to create a new menu item
    @PostMapping("/new")
    public String createMenu(@ModelAttribute Menu menu) {
        menuService.createMenu(menu);
        return "redirect:/menus";
    }

    // Display form to update a menu item
    @GetMapping("/edit/{menuId}")
    public String showUpdateForm(@PathVariable int menuId, Model model) {
        Optional<Menu> menu = menuService.getMenuById(menuId);
        if (menu.isPresent()) {
            model.addAttribute("menu", menu.get());
            return "menu-form";
        } else {
            return "error";
        }
    }

    // Handle form submission to update a menu item
    @PostMapping("/edit/{menuId}")
    public String updateMenu(@PathVariable int menuId, @ModelAttribute Menu updatedMenu) {
        menuService.updateMenu(menuId, updatedMenu);
        return "redirect:/menus";
    }

    // Delete a menu item
    @GetMapping("/delete/{menuId}")
    public String deleteMenu(@PathVariable int menuId) {
        menuService.deleteMenu(menuId);
        return "redirect:/menus";
    }

    // Add new mappings for cart, order success, and tracking
    @GetMapping("/cart")
    public String showCart(Model model) {
        return "cart"; // Refers to cart.html in templates/
    }

    @GetMapping("/order-success")
    public String showOrderSuccess(Model model) {
        return "order-success"; // Refers to order-success.html in templates/
    }

    @GetMapping("/track-order")
    public String showTrackOrder(Model model) {
        return "track-order"; // Refers to track-order.html in templates/
    }
}