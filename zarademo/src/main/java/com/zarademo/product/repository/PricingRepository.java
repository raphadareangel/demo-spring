package com.zarademo.product.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zarademo.product.model.Pricing;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {

    List<Pricing> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate );
}

