package com.zarademo.product.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zarademo.product.model.Pricing;
import com.zarademo.product.repository.PricingRepository;

@WebMvcTest(PricingController.class)
public class PricingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricingRepository pricingRepository;
    
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("request at 10:00 on day 14 of product 35455 for brand 1")
    public void testGetPricing1() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(2023, 6, 14, 10, 00);
        Pricing pricing = new Pricing();
        pricing.setPrice(35.50f);
        pricing.setPriority(1);
        pricing.setProductId(35455L);
        pricing.setBrandId(1L);
        Pricing pricing2 = new Pricing();
        pricing2.setPrice(40.50f);
        pricing2.setPriority(0); // more priority
        pricing2.setProductId(35455L);
        pricing2.setBrandId(1L);

        when(pricingRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1L, startDateTime,startDateTime))
                .thenReturn(Arrays.asList(pricing, pricing2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pricing")
                .param("applicationDate", "2023-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")).andReturn();
        
        Pricing pricingResponse = extractResponse(result, Pricing.class);
        assertThat(pricingResponse).isNotNull();
		assertEquals(pricing2.getPrice(), pricingResponse.getPrice());
    }
    
    @Test
    @DisplayName("request at 4:00 p.m. on day 14 of product 35455 for brand 1")
    public void testGetPricing2() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(2023, 6, 14, 16, 00);
        LocalDateTime endDateTime = startDateTime;
        Pricing pricing = new Pricing();
        pricing.setPrice(35.50f);
        pricing.setPriority(1);
        pricing.setProductId(35455L);
        pricing.setBrandId(1L);
        Pricing pricing2 = new Pricing();
        pricing2.setPrice(40.50f);
        pricing2.setPriority(0); // more priority
        pricing2.setProductId(35455L);
        pricing2.setBrandId(1L);

        when(pricingRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1L, startDateTime, endDateTime))
                .thenReturn(Arrays.asList(pricing, pricing2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pricing")
                .param("applicationDate", "2023-06-14T16:00:00")
                .param("productId", "35455")
                .param("brandId", "1")).andReturn();
        
        Pricing pricingResponse = extractResponse(result, Pricing.class);
        assertThat(pricingResponse).isNotNull();
		assertEquals(pricing2.getPrice(), pricingResponse.getPrice());
    }
    
    @Test
    @DisplayName("request at 9:00 p.m. on day 14 of product 35455 for brand 1")
    public void testGetPricing3() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(2023, 6, 14, 21, 00);
        LocalDateTime endDateTime = startDateTime;
        Pricing pricing = new Pricing();
        pricing.setPrice(35.50f);
        pricing.setPriority(1);
        pricing.setProductId(35455L);
        pricing.setBrandId(1L);
        Pricing pricing2 = new Pricing();
        pricing2.setPrice(40.50f);
        pricing2.setPriority(0); // more priority
        pricing2.setProductId(35455L);
        pricing2.setBrandId(1L);

        when(pricingRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1L, startDateTime, endDateTime))
                .thenReturn(Arrays.asList(pricing, pricing2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pricing")
                .param("applicationDate", "2023-06-14T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1")).andReturn();
        
        Pricing pricingResponse = extractResponse(result, Pricing.class);
        assertThat(pricingResponse).isNotNull();
		assertEquals(pricing2.getPrice(), pricingResponse.getPrice());
    }
    
    @Test
    @DisplayName("request at 10:00 on day 15 of product 35455 for brand 1")
    public void testGetPricing4() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(2023, 6, 15, 10, 00);
        LocalDateTime endDateTime = startDateTime;
        Pricing pricing = new Pricing();
        pricing.setPrice(35.50f);
        pricing.setPriority(1);
        pricing.setProductId(35455L);
        pricing.setBrandId(1L);
        Pricing pricing2 = new Pricing();
        pricing2.setPrice(40.50f);
        pricing2.setPriority(0); // more priority
        pricing2.setProductId(35455L);
        pricing2.setBrandId(1L);

        when(pricingRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1L, startDateTime, endDateTime))
                .thenReturn(Arrays.asList(pricing, pricing2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pricing")
                .param("applicationDate", "2023-06-15T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")).andReturn();
        
        Pricing pricingResponse = extractResponse(result, Pricing.class);
        assertThat(pricingResponse).isNotNull();
		assertEquals(pricing2.getPrice(), pricingResponse.getPrice());
    }
    
    @Test
    @DisplayName("request at 9:00 p.m. on the 16th of product 35455 for brand 1")
    public void testGetPricing5() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(2023, 6, 16, 21, 00);
        LocalDateTime endDateTime = startDateTime;
        Pricing pricing = new Pricing();
        pricing.setPrice(35.50f);
        pricing.setPriority(1);
        pricing.setProductId(35455L);
        pricing.setBrandId(1L);
        Pricing pricing2 = new Pricing();
        pricing2.setPrice(40.50f);
        pricing2.setPriority(0); // more priority
        pricing2.setProductId(35455L);
        pricing2.setBrandId(1L);

        when(pricingRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1L, startDateTime, endDateTime))
                .thenReturn(Arrays.asList(pricing, pricing2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pricing")
                .param("applicationDate", "2023-06-16T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1")).andReturn();
        
        Pricing pricingResponse = extractResponse(result, Pricing.class);
        assertThat(pricingResponse).isNotNull();
		assertEquals(pricing2.getPrice(), pricingResponse.getPrice());
    }
    
    private <T> T extractResponse(MvcResult mvcResult, Class<T> clazz) throws Exception {
		return mapper.readValue(mvcResult.getResponse().getContentAsString(), clazz);
	}
    @Test
    @DisplayName("request all prices")
    public void testGetAllPricing() throws Exception {
        Pricing pricing = new Pricing();
        pricing.setPrice(35.50f);
        pricing.setPriority(1);
        pricing.setProductId(35455L);
        pricing.setBrandId(1L);
        Pricing pricing2 = new Pricing();
        pricing2.setPrice(40.50f);
        pricing2.setPriority(0); // more priority
        pricing2.setProductId(35455L);
        pricing2.setBrandId(1L);

        when(pricingRepository.findAll())
                .thenReturn(Arrays.asList(pricing, pricing2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pricing")
                ).andReturn();
        
        assertNotNull(result);
    }
    
}

