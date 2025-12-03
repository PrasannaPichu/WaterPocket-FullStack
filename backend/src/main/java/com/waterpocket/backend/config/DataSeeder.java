package com.waterpocket.backend.config;

import com.waterpocket.backend.model.Product;
import com.waterpocket.backend.model.User;
import com.waterpocket.backend.repository.OrderRepository; // Import OrderRepo
import com.waterpocket.backend.repository.ProductRepository;
import com.waterpocket.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepo, UserRepository userRepo, OrderRepository orderRepo) {
        return args -> {
            // 1. WIPE DATA FOR FRESH START (Deletes all previous orders/products)
            orderRepo.deleteAll(); 
            productRepo.deleteAll();
            
            // 2. RESET PRODUCTS
            productRepo.saveAll(Arrays.asList(
                createProduct("20L", "20L Water Can", 120, 40.0, 30.0),
                createProduct("10L", "10L Water Can", 50, 25.0, 20.0),
                createProduct("PartyBox", "Party Box (24pcs)", 200, 300.0, 250.0)
            ));
            System.out.println("--- SYSTEM RESET: Orders Cleared & Products Reset ---");

            // 3. ENSURE ADMIN EXISTS
            if (userRepo.findByEmail("prasannasomasekar@gmail.com") == null) {
                User admin = new User();
                admin.setName("Prasanna (Owner)");
                admin.setEmail("prasannasomasekar@gmail.com");
                admin.setPassword("prasanna123");
                admin.setRole("admin");
                admin.setWalletBalance(0.0);
                userRepo.save(admin);
            }
        };
    }

    private Product createProduct(String id, String name, Integer stock, Double p, Double r) {
        Product prod = new Product();
        prod.setId(id); prod.setName(name); prod.setStock(stock);
        prod.setPriceP(p); prod.setPriceR(r);
        return prod;
    }
}