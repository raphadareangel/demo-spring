package com.zarademo.product.controller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zarademo.product.model.Pricing;
import com.zarademo.product.repository.PricingRepository;

@RestController
public class PricingController {
	
	private PricingRepository repository;
	
    public PricingController(PricingRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/pricing")
    public Pricing getPricing(
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {

        List<Pricing> pricingList = repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId, applicationDate, applicationDate);
		
        //function to get pricing for highest priority
        Pricing pricing = pricingList.stream().min(Comparator.comparingInt(Pricing::getPriority)).orElse(null);
 
        
        return pricing;

        
    }
	
	@GetMapping("/pricing")
	private List<Pricing> getAllPrice() {
		return repository.findAll();
	}
}

