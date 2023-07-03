package com.inditext.zara.model;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Comment getter and setter tags if you do not have lombock
 */
@Getter
@Setter
@Entity
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    
    @ManyToMany
    @JoinTable(
        name = "product_pricing",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "pricing_id")
    )
    private List<Product> products;
    
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String priceList;
    
    private Integer priority;
    private Float price;
    private String currency;


    public Pricing() {
    }

    public Pricing(LocalDateTime startDate, LocalDateTime endDate, String priceList,
                   Integer priority, Float price, String currency) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }
    
    /**
     * Uncomment getters and setters if you do not have lombock in your IDE
     */
    	    // Getters and setters
    
//    public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//
//	public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Brand getBrand() {
//		return brand;
//	}
//
//	public void setBrand(Brand brand) {
//		this.brand = brand;
//	}
//
//	public LocalDateTime getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDateTime startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDateTime getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDateTime endDate) {
//        this.endDate = endDate;
//    }
//
//    public String getPriceList() {
//        return priceList;
//    }
//
//    public void setPriceList(String priceList) {
//        this.priceList = priceList;
//    }
//
//    public Integer getPriority() {
//        return priority;
//    }
//
//    public void setPriority(Integer priority) {
//        this.priority = priority;
//    }
//
//    public Float getPrice() {
//        return price;
//    }
//
//    public void setPrice(Float price) {
//        this.price = price;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
}

