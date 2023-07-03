package com.inditext.zara.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Comment getter and setter tags if you do not have lombock
 */
@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pricing> prices;
    
    public Brand() {
		super();
	}
    
    public Brand(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Brand(String name, List<Pricing> prices) {
		this.name = name;
		this.prices = prices;
	}
	
    /**
     * Uncomment getters and setters if you do not have lombock in your IDE
     */
    	    // Getters and setters
	
//    public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public List<Pricing> getPrices() {
//        return prices;
//    }
//
//    public void setPrices(List<Pricing> prices) {
//        this.prices = prices;
//    }
}

