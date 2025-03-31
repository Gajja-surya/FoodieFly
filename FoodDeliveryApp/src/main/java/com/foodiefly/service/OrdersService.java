package com.foodiefly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodiefly.entity.Orders;
import com.foodiefly.repository.OrdersRepository;

public interface OrdersService {
    Orders createOrder(Orders order);
    Optional<Orders> getOrderById(int orderId);
    List<Orders> getAllOrders();
    void deleteOrder(int orderId);
}

@Service
class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    @Override
    public Optional<Orders> getOrderById(int orderId) {
        return ordersRepository.findById(orderId);
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public void deleteOrder(int orderId) {
        ordersRepository.deleteById(orderId);
    }
}
