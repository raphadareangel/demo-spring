package com.inditext.zara.controller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inditext.zara.exception.ResourceNotFoundException;
import com.inditext.zara.model.Pricing;
import com.inditext.zara.service.PricingService;

@RestController
@RequestMapping("/pricing")
public class PricingController {
	
	private final PricingService pricingService;

    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

	@PostMapping("/list")
    public Pricing getPricing(
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {

        List<Pricing> pricingList = pricingService.getPriceListBaseOnDate(productId, brandId, applicationDate);
        
        if(pricingList.isEmpty()) 
        	throw new ResourceNotFoundException("No Pricing list found for product:" + productId +
        		" brand:" +brandId +" and dates:" + applicationDate);
		
        //function to get pricing for highest priority
        Pricing pricing = pricingList.stream().min(Comparator.comparingInt(Pricing::getPriority)).orElseThrow(() -> new RuntimeException("No pricing data available."));
 
        
        return pricing;
    }
	
	@PostMapping
    public ResponseEntity<Pricing> createPricing(@RequestBody Pricing pricing) {
        Pricing createdPricing = pricingService.createPricing(pricing);
        return new ResponseEntity<>(createdPricing, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pricing> getPricingById(@PathVariable Long id) {
    	return new ResponseEntity<>(pricingService.getPricingById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Pricing>> getAllPricing() {
        List<Pricing> pricings = pricingService.getAllPricing();
        return new ResponseEntity<>(pricings, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pricing> updatePricing(@PathVariable Long id, @RequestBody Pricing pricing) {
            return new ResponseEntity<>(pricingService.updatePricing(id, pricing), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePricing(@PathVariable Long id) {
        pricingService.getPricingById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

