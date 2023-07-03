package com.inditext.zara.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inditext.zara.exception.ResourceNotFoundException;
import com.inditext.zara.model.Brand;
import com.inditext.zara.model.Pricing;
import com.inditext.zara.model.Product;
import com.inditext.zara.repository.PricingRepository;

@ExtendWith(MockitoExtension.class)
public class PricingServiceTest {

    @Mock
    private PricingRepository pricingRepository;

    @Mock
    private ProductService productService;

    @Mock
    private BrandService brandService;

    @InjectMocks
    private PricingService pricingService;

    @Test
    public void testCreatePricing() {
        Pricing pricing = new Pricing();
        Mockito.when(pricingRepository.save(pricing)).thenReturn(pricing);

        Pricing createdPricing = pricingService.createPricing(pricing);

        Assertions.assertEquals(pricing, createdPricing);
        Mockito.verify(pricingRepository, Mockito.times(1)).save(pricing);
    }

    @Test
    public void testGetPricingByIdExists() {
        Long id = 1L;
        Pricing pricing = new Pricing();
        Mockito.when(pricingRepository.findById(id)).thenReturn(Optional.of(pricing));

        Pricing retrievedPricing = pricingService.getPricingById(id);

        Assertions.assertEquals(pricing, retrievedPricing);
        Mockito.verify(pricingRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void testGetPricingByIdNotExists() {
        Long id = 1L;
        Mockito.when(pricingRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            pricingService.getPricingById(id);
        });
        Mockito.verify(pricingRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void testGetAllPricing() {
        List<Pricing> pricingList = new ArrayList<>();
        Mockito.when(pricingRepository.findAll()).thenReturn(pricingList);

        List<Pricing> retrievedList = pricingService.getAllPricing();

        Assertions.assertEquals(pricingList, retrievedList);
        Mockito.verify(pricingRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testUpdatePricing() {
        Long id = 1L;
        Pricing existingPricing = new Pricing();
        Pricing updatedPricing = new Pricing();
        Mockito.when(pricingRepository.findById(id)).thenReturn(Optional.of(existingPricing));
        Mockito.when(pricingRepository.save(Mockito.any(Pricing.class))).thenReturn(existingPricing);

        Pricing result = pricingService.updatePricing(id, updatedPricing);

        Assertions.assertEquals(existingPricing, result);
        Mockito.verify(pricingRepository, Mockito.times(1)).findById(id);
        Mockito.verify(pricingRepository, Mockito.times(1)).save(Mockito.any(Pricing.class));
    }

    @Test
    public void testUpdatePricing_NotExists() {
        Long id = 1L;
        Pricing updatedPricing = new Pricing();
        Mockito.when(pricingRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            pricingService.updatePricing(id, updatedPricing);
        });
        Mockito.verify(pricingRepository, Mockito.times(1)).findById(id);
        Mockito.verify(pricingRepository, Mockito.never()).save(Mockito.any(Pricing.class));
    }

    @Test
    public void testDeletePricing() {
        Long id = 1L;
        Pricing existingPricing = new Pricing();
        Mockito.when(pricingRepository.findById(id)).thenReturn(Optional.of(existingPricing));

        pricingService.deletePricing(id);

        Mockito.verify(pricingRepository, Mockito.times(1)).findById(id);
        Mockito.verify(pricingRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testDeletePricingNotExists() {
        Long id = 1L;
        Mockito.when(pricingRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            pricingService.deletePricing(id);
        });
        Mockito.verify(pricingRepository, Mockito.times(1)).findById(id);
        Mockito.verify(pricingRepository, Mockito.never()).deleteById(Mockito.anyLong());
    }

    @Test
    public void testGetPriceListBaseOnDate() {
        Long productId = 1L;
        Long brandId = 2L;
        LocalDateTime inputDate = LocalDateTime.now();

        Brand brand = new Brand(1L, "Brand");
        Pricing pricing1 = new Pricing();
        pricing1.setStartDate(inputDate.minusDays(2));
        pricing1.setEndDate(inputDate.plusDays(2));
        Pricing pricing2 = new Pricing();
        pricing2.setStartDate(inputDate.minusDays(1));
        pricing2.setEndDate(inputDate.plusDays(1));
        Pricing pricing3 = new Pricing();
        pricing3.setStartDate(inputDate.plusDays(1));
        pricing3.setEndDate(inputDate.plusDays(3));
        List<Pricing> pricingList = new ArrayList<>();
        pricingList.add(pricing1);
        pricingList.add(pricing2);
        pricingList.add(pricing3);
        brand.setPrices(pricingList);

        Product product = new Product(1L, "Prduct1");
        List<Product> products = List.of(product);
        pricing1.setProducts(products);
        pricing2.setProducts(products);
        pricing3.setProducts(products);

        Mockito.when(productService.getProductById(productId)).thenReturn(product);
        Mockito.when(brandService.getBrandById(brandId)).thenReturn(brand);

        List<Pricing> output = pricingService.getPriceListBaseOnDate(productId, brandId, inputDate);

        Assertions.assertEquals(2, output.size());
        Assertions.assertTrue(output.contains(pricing1));
        Assertions.assertTrue(output.contains(pricing2));

        Mockito.verify(productService, Mockito.times(1)).getProductById(productId);
        Mockito.verify(brandService, Mockito.times(1)).getBrandById(brandId);
    }
}

