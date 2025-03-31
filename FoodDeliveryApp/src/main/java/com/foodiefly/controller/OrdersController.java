package com.foodiefly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.foodiefly.entity.Orders;
import com.foodiefly.entity.Restaurant;
import com.foodiefly.entity.User;
import com.foodiefly.repository.RestaurantRepository;
import com.foodiefly.repository.UserRepository;
import com.foodiefly.service.OrdersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // DTO for controlled response
    public static class OrderResponseDTO {
        private int orderId;
        private double totalAmount;
        private String status;
        private String paymentMode;
        private int userId;
        private int restaurantId;
        private List<Orders.OrderItem> items;

        public OrderResponseDTO(Orders order) {
            this.orderId = order.getOrderId();
            this.totalAmount = order.getTotalAmount();
            this.status = order.getStatus().name();
            this.paymentMode = order.getPaymentMode().name();
            this.userId = order.getUser().getUserId();
            this.restaurantId = order.getRestaurant().getRestaurantId();
            this.items = order.getItems();
        }

        // Getters
        public int getOrderId() { return orderId; }
        public double getTotalAmount() { return totalAmount; }
        public String getStatus() { return status; }
        public String getPaymentMode() { return paymentMode; }
        public int getUserId() { return userId; }
        public int getRestaurantId() { return restaurantId; }
        public List<Orders.OrderItem> getItems() { return items; }
    }

    @GetMapping
    public String showOrdersPage(Model model) {
        List<Orders> orders = ordersService.getAllOrders();
        List<User> users = userRepository.findAll();
        List<Restaurant> restaurants = restaurantRepository.findAll();

        model.addAttribute("orders", orders);
        model.addAttribute("users", users);
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("order", new Orders());
        model.addAttribute("orderStatuses", Orders.OrderStatus.values());
        model.addAttribute("paymentModes", Orders.PaymentMode.values());

        return "orders";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createOrder(@RequestBody Orders orderRequest) {
        logger.info("Received order request: {}", orderRequest);
        try {
            Optional<User> user = userRepository.findById(orderRequest.getUser().getUserId());
            Optional<Restaurant> restaurant = restaurantRepository.findById(orderRequest.getRestaurant().getRestaurantId());

            if (!user.isPresent()) {
                logger.warn("User not found: {}", orderRequest.getUser().getUserId());
                return ResponseEntity.badRequest().body("{\"error\": \"User not found\"}");
            }
            if (!restaurant.isPresent()) {
                logger.warn("Restaurant not found: {}", orderRequest.getRestaurant().getRestaurantId());
                return ResponseEntity.badRequest().body("{\"error\": \"Restaurant not found\"}");
            }

            orderRequest.setUser(user.get());
            orderRequest.setRestaurant(restaurant.get());

            Orders createdOrder = ordersService.createOrder(orderRequest);
            logger.info("Order created successfully: {}", createdOrder);
            return ResponseEntity.ok(new OrderResponseDTO(createdOrder));
        } catch (Exception e) {
            logger.error("Error creating order", e);
            return ResponseEntity.status(500).body("{\"error\": \"Internal server error: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{orderId}")
    @ResponseBody
    public ResponseEntity<?> getOrderById(@PathVariable("orderId") int orderId) {
        logger.info("Fetching order with ID: {}", orderId);
        try {
            Optional<Orders> order = ordersService.getOrderById(orderId);
            if (order.isPresent()) {
                return ResponseEntity.ok(new OrderResponseDTO(order.get()));
            } else {
                logger.warn("Order not found: {}", orderId);
                return ResponseEntity.badRequest().body("{\"error\": \"Order not found\"}");
            }
        } catch (Exception e) {
            logger.error("Error fetching order", e);
            return ResponseEntity.status(500).body("{\"error\": \"Internal server error: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable("orderId") int orderId) {
        ordersService.deleteOrder(orderId);
        return "redirect:/orders";
    }
}