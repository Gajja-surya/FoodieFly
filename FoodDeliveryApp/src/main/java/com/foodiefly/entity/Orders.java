package com.foodiefly.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode", nullable = false)
    private PaymentMode paymentMode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items;

    // Enums
    public enum OrderStatus {
        PENDING, PROCESSING, DELIVERED, CANCELLED
    }

    public enum PaymentMode {
        CASH, CARD, UPI, WALLET
    }

    // Embeddable class for order items
    @Embeddable
    public static class OrderItem {
        @Column(name = "item_id")
        private int itemId;

        @Column(name = "name")
        private String name;

        @Column(name = "price")
        private double price;

        @Column(name = "image_path")
        private String imagePath;

        @Column(name = "quantity")
        private int quantity;

        // Default constructor
        public OrderItem() {}

        // Parameterized constructor
        public OrderItem(int itemId, String name, double price, String imagePath, int quantity) {
            this.itemId = itemId;
            this.name = name;
            this.price = price;
            this.imagePath = imagePath;
            this.quantity = quantity;
        }

        // Getters and Setters
        public int getItemId() { return itemId; }
        public void setItemId(int itemId) { this.itemId = itemId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
        public String getImagePath() { return imagePath; }
        public void setImagePath(String imagePath) { this.imagePath = imagePath; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    // Constructors
    public Orders() {}

    public Orders(double totalAmount, OrderStatus status, PaymentMode paymentMode, User user, Restaurant restaurant, List<OrderItem> items) {
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
        this.user = user;
        this.restaurant = restaurant;
        this.items = items;
    }

    @PrePersist
    protected void onCreate() {
        this.orderDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public PaymentMode getPaymentMode() { return paymentMode; }
    public void setPaymentMode(PaymentMode paymentMode) { this.paymentMode = paymentMode; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    @Override
    public String toString() {
        return "Orders [orderId=" + orderId + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + ", status="
                + status + ", paymentMode=" + paymentMode + ", user=" + user + ", restaurant=" + restaurant + ", items=" + items + "]";
    }
}