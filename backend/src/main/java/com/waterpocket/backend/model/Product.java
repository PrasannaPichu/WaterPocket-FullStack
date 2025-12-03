package com.waterpocket.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private Integer stock;
    private Double priceP; // Personal Price
    private Double priceR; // Retailer Price
    private String description;
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Double getPriceP() { return priceP; }
    public void setPriceP(Double priceP) { this.priceP = priceP; }
    public Double getPriceR() { return priceR; }
    public void setPriceR(Double priceR) { this.priceR = priceR; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}