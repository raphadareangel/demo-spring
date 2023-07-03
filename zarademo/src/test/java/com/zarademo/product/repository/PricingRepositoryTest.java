package com.zarademo.product.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.zarademo.product.model.Pricing;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class PricingRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PricingRepository pricingRepository;

    @Test
    public void testFindByProductIdAndBrandIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual() {
        // Create test data
    	LocalDateTime inputDate = LocalDateTime.of(2023, 6, 14, 21, 0, 0);
        LocalDateTime startDate = LocalDateTime.of(2023, 6, 14, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 6, 14, 23, 59, 59);
        Pricing pricing1 = new Pricing(1L, startDate, endDate, "1", 35455L, 0, 35.50f, "EUR");
        entityManager.persist(pricing1);
        Pricing pricing2 = new Pricing(1L, startDate, endDate, "3", 35455L, 1, 30.50f, "EUR");
        entityManager.persist(pricing2);
        entityManager.flush();

        // Perform the repository query
        List<Pricing> result = pricingRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1L, inputDate, inputDate);

        // Verify the result
        assertEquals(2, result.size());
        Pricing foundPricing = result.get(0);
        assertEquals(pricing1, foundPricing);
    }
    
    @Test
    public void testFindAll() {
      

        // Perform the repository query
        List<Pricing> result = pricingRepository.findAll();

        // Verify the result
        assertEquals(4, result.size());
        
    }
}

