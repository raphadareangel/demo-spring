package com.inditext.zara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditext.zara.model.Pricing;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {

}

