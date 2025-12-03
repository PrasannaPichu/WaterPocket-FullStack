package com.waterpocket.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document(collection = "orders")
public class Order {
    @Id private String id;
    private String customerId;
    private String customerName;
    private String mobileNumber;
    private Object location; 
    
    private String houseType;
    private Integer floorNumber;
    private Boolean hasLift;

    private Map<String, Integer> items;
    private Boolean needsCustomization;
    private String customText;
    private String customImage; 
    private String partyBoxSize;

    // --- FINANCIAL UPDATES ---
    private Double totalAmount; // Current amount
    private Double previousAmount; // Amount before edit (for comparison)
    private Double walletDeduction;
    private String paymentMethod; // "UPI", "Card", "COD", "Wallet"
    private String paymentStatus; // "Pending", "Paid", "Refunded"

    private String status; 
    private String orderDate;

    // Subscription
    private String bookingType; 
    private String subDuration; 
    private String subFrequency; 
    private String subscriptionStatus; 
    private Integer totalDeliveriesNeeded; 
    private Integer deliveredCount; 
    private List<String> deliveryLogs = new ArrayList<>(); 
    private List<String> pausedDates = new ArrayList<>(); 

    // Getters & Setters (Lombok @Data recommended, but writing out for safety)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public Object getLocation() { return location; }
    public void setLocation(Object location) { this.location = location; }
    public String getHouseType() { return houseType; }
    public void setHouseType(String houseType) { this.houseType = houseType; }
    public Integer getFloorNumber() { return floorNumber; }
    public void setFloorNumber(Integer floorNumber) { this.floorNumber = floorNumber; }
    public Boolean getHasLift() { return hasLift; }
    public void setHasLift(Boolean hasLift) { this.hasLift = hasLift; }
    public Map<String, Integer> getItems() { return items; }
    public void setItems(Map<String, Integer> items) { this.items = items; }
    public Boolean getNeedsCustomization() { return needsCustomization; }
    public void setNeedsCustomization(Boolean needsCustomization) { this.needsCustomization = needsCustomization; }
    public String getCustomText() { return customText; }
    public void setCustomText(String customText) { this.customText = customText; }
    public String getCustomImage() { return customImage; }
    public void setCustomImage(String customImage) { this.customImage = customImage; }
    public String getPartyBoxSize() { return partyBoxSize; }
    public void setPartyBoxSize(String partyBoxSize) { this.partyBoxSize = partyBoxSize; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public Double getPreviousAmount() { return previousAmount; }
    public void setPreviousAmount(Double previousAmount) { this.previousAmount = previousAmount; }
    public Double getWalletDeduction() { return walletDeduction; }
    public void setWalletDeduction(Double walletDeduction) { this.walletDeduction = walletDeduction; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
    public String getBookingType() { return bookingType; }
    public void setBookingType(String bookingType) { this.bookingType = bookingType; }
    public String getSubDuration() { return subDuration; }
    public void setSubDuration(String subDuration) { this.subDuration = subDuration; }
    public String getSubFrequency() { return subFrequency; }
    public void setSubFrequency(String subFrequency) { this.subFrequency = subFrequency; }
    public String getSubscriptionStatus() { return subscriptionStatus; }
    public void setSubscriptionStatus(String subscriptionStatus) { this.subscriptionStatus = subscriptionStatus; }
    public Integer getTotalDeliveriesNeeded() { return totalDeliveriesNeeded; }
    public void setTotalDeliveriesNeeded(Integer totalDeliveriesNeeded) { this.totalDeliveriesNeeded = totalDeliveriesNeeded; }
    public Integer getDeliveredCount() { return deliveredCount; }
    public void setDeliveredCount(Integer deliveredCount) { this.deliveredCount = deliveredCount; }
    public List<String> getDeliveryLogs() { return deliveryLogs; }
    public void setDeliveryLogs(List<String> deliveryLogs) { this.deliveryLogs = deliveryLogs; }
    public List<String> getPausedDates() { return pausedDates; }
    public void setPausedDates(List<String> pausedDates) { this.pausedDates = pausedDates; }
}