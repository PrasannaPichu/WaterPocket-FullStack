package com.waterpocket.backend.controller;

import com.waterpocket.backend.model.Order;
import com.waterpocket.backend.model.User;
import com.waterpocket.backend.repository.OrderRepository;
import com.waterpocket.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public List<Order> getUserOrders(@PathVariable String id) {
        return orderRepository.findByCustomerId(id);
    }

    @PostMapping("/place")
    public Order placeOrder(@RequestBody Order order) {
        order.setStatus("Placed");
        // FIX: Use ISO 8601 Date format
        order.setOrderDate(Instant.now().toString());
        order.setPaymentStatus("COD".equals(order.getPaymentMethod()) ? "Pending" : "Paid");

        // Deduct Wallet
        if (order.getWalletDeduction() != null && order.getWalletDeduction() > 0) {
            User user = userRepository.findById(order.getCustomerId()).orElse(null);
            if (user != null) {
                user.setWalletBalance(user.getWalletBalance() - order.getWalletDeduction());
                userRepository.save(user);
            }
        }
        return orderRepository.save(order);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Map<String, Object> payload) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) return ResponseEntity.notFound().build();

        User user = userRepository.findById(order.getCustomerId()).orElse(null);

        // ---------------------------------------------------------
        // 1. ROBUST DELIVERY LOGIC (Fixes 500 Error)
        // ---------------------------------------------------------
        if (payload.containsKey("incrementDelivery")) {
            Object val = payload.get("incrementDelivery");
            int countToAdd = 0;

            // Handle if frontend sends a Number (e.g., 2, 3)
            if (val instanceof Number) {
                countToAdd = ((Number) val).intValue();
            }
            // Handle if frontend sends a String (e.g., "2")
            else if (val instanceof String) {
                try {
                    countToAdd = Integer.parseInt((String) val);
                } catch (NumberFormatException e) {
                    countToAdd = 0;
                }
            }
            // Handle legacy Boolean (true = 1)
            else if (val instanceof Boolean && (Boolean) val) {
                countToAdd = 1;
            }

            if (countToAdd > 0) {
                int current = order.getDeliveredCount() == null ? 0 : order.getDeliveredCount();
                int total = order.getTotalDeliveriesNeeded() == null ? 1 : order.getTotalDeliveriesNeeded();

                // Update count
                order.setDeliveredCount(current + countToAdd);

                // Check if subscription is complete
                if (order.getDeliveredCount() >= total) {
                    order.setSubscriptionStatus("Completed");
                    order.setStatus("Delivered");
                }

                // Add to logs safely
                if (order.getDeliveryLogs() == null) {
                    order.setDeliveryLogs(new ArrayList<>()); // Ensure list exists
                }
                order.getDeliveryLogs().add(Instant.now().toString());
            }
        }

        // ---------------------------------------------------------
        // 2. STATUS UPDATE & REFUND LOGIC
        // ---------------------------------------------------------
        if (payload.containsKey("status")) {
            String newStatus = (String) payload.get("status");
            
            // Handle Refund if Cancelling
            if ("Cancelled".equals(newStatus) && !"Cancelled".equals(order.getStatus())) {
                double refund = 0;
                
                // Pro-rata refund for subscriptions
                if ("subscription".equals(order.getBookingType())) {
                    int total = order.getTotalDeliveriesNeeded() != null ? order.getTotalDeliveriesNeeded() : 1;
                    int done = order.getDeliveredCount() != null ? order.getDeliveredCount() : 0;
                    if (done < total) {
                        refund = (order.getTotalAmount() / total) * (total - done);
                    }
                }
                // Full refund for one-time (if online pay)
                else if (!"COD".equals(order.getPaymentMethod())) {
                    refund = order.getTotalAmount();
                }

                if (refund > 0 && user != null) {
                    user.setWalletBalance(user.getWalletBalance() + refund);
                    userRepository.save(user);
                }
            }
            order.setStatus(newStatus);
        }

        // ---------------------------------------------------------
        // 3. GENERIC DATA UPDATE (Edit Order)
        // ---------------------------------------------------------
        if (payload.containsKey("totalAmount")) {
            double oldTotal = order.getTotalAmount();
            double newTotal = ((Number) payload.get("totalAmount")).doubleValue();

            // Refund difference if price drops after edit
            if (newTotal < oldTotal && !"COD".equals(order.getPaymentMethod()) && user != null) {
                user.setWalletBalance(user.getWalletBalance() + (oldTotal - newTotal));
                userRepository.save(user);
            }
            
            order.setTotalAmount(newTotal);
        }

        if (payload.containsKey("items")) {
            order.setItems((Map<String, Integer>) payload.get("items"));
        }
        if (payload.containsKey("location")) {
            order.setLocation(payload.get("location")); // Assumes location object matches
        }
        if (payload.containsKey("paymentMethod")) {
            order.setPaymentMethod((String) payload.get("paymentMethod"));
        }
        if (payload.containsKey("walletDeduction")) {
             order.setWalletDeduction(((Number) payload.get("walletDeduction")).doubleValue());
        }

        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    @PutMapping("/{id}/toggle-sub")
    public Order toggleSub(@PathVariable String id, @RequestBody Map<String, String> action) {
        return orderRepository.findById(id).map(o -> {
            o.setSubscriptionStatus(action.get("action").equals("pause") ? "Paused" : "Active");
            return orderRepository.save(o);
        }).orElse(null);
    }
}