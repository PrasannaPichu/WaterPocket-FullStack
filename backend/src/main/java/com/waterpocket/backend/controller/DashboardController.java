package com.waterpocket.backend.controller;

import com.waterpocket.backend.model.Order;
import com.waterpocket.backend.repository.OrderRepository;
import com.waterpocket.backend.repository.ProductRepository;
import com.waterpocket.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class DashboardController {

    @Autowired private OrderRepository orderRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private UserRepository userRepository;

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        List<Order> orders = orderRepository.findAll();
        double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        long totalOrders = orders.size();
        long totalUsers = userRepository.count();
        long lowStock = productRepository.findAll().stream().filter(p -> p.getStock() < 10).count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalSales", totalSales);
        stats.put("totalOrders", totalOrders);
        stats.put("totalUsers", totalUsers);
        stats.put("lowStock", lowStock);
        return stats;
    }
}