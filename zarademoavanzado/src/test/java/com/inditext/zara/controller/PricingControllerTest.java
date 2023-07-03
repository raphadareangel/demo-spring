package com.inditext.zara.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import java.util.List;

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

import com.inditext.zara.model.Pricing;
import com.inditext.zara.service.PricingService;
import com.inditext.zara.util.PricingMock;

@WebMvcTest(PricingController.class)
public class PricingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PricingService pricingService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("request at 10:00 on day 14 of product 35455 for brand 1")
	public void testGetPricing1() throws Exception {
		LocalDateTime inputDate = LocalDateTime.of(2023, 6, 14, 10, 00);
		List<Pricing> pricings = PricingMock.getData();

		when(pricingService.getPriceListBaseOnDate(35455L, 1L, inputDate)).thenReturn(pricings);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pricing/list")
				.param("applicationDate", "2023-07-14T10:00:00").param("productId", "1").param("brandId", "1"))
				.andReturn();

		assertNotNull(result);
	}

	@Test
	@DisplayName("request at 4:00 p.m. on day 14 of product 35455 for brand 1")
	public void testGetPricing2() throws Exception {
		LocalDateTime inputDate = LocalDateTime.of(2023, 6, 14, 16, 00);
		List<Pricing> pricings = PricingMock.getData();

		when(pricingService.getPriceListBaseOnDate(35455L, 1L, inputDate)).thenReturn(pricings);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pricing/list")
				.param("applicationDate", "2023-06-14T16:00:00").param("productId", "35455").param("brandId", "1"))
				.andReturn();

		assertNotNull(result);
	}

	@Test
	@DisplayName("request at 9:00 p.m. on day 14 of product 35455 for brand 1")
	public void testGetPricing3() throws Exception {
		LocalDateTime inputDate = LocalDateTime.of(2023, 6, 14, 21, 00);
		List<Pricing> pricings = PricingMock.getData();

		when(pricingService.getPriceListBaseOnDate(35455L, 1L, inputDate)).thenReturn(pricings);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pricing/list")
				.param("applicationDate", "2023-06-14T21:00:00").param("productId", "35455").param("brandId", "1"))
				.andReturn();

		assertNotNull(result);
	}

	@Test
	@DisplayName("request at 10:00 on day 15 of product 35455 for brand 1")
	public void testGetPricing4() throws Exception {
		LocalDateTime inputDate = LocalDateTime.of(2023, 6, 15, 10, 00);
		List<Pricing> pricings = PricingMock.getData();

		when(pricingService.getPriceListBaseOnDate(35455L, 1L, inputDate)).thenReturn(pricings);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pricing/list")
				.param("applicationDate", "2023-06-15T10:00:00").param("productId", "35455").param("brandId", "1"))
				.andReturn();

		assertNotNull(result);
		
	}

	@Test
	@DisplayName("request at 9:00 p.m. on the 16th of product 35455 for brand 1")
	public void testGetPricing5() throws Exception {
		LocalDateTime inputDate = LocalDateTime.of(2023, 6, 16, 21, 00);
		List<Pricing> pricings = PricingMock.getData();

		when(pricingService.getPriceListBaseOnDate(35455L, 1L, inputDate)).thenReturn(pricings);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pricing/list")
				.param("applicationDate", "2023-06-16T21:00:00").param("productId", "35455").param("brandId", "1"))
				.andReturn();

		assertNotNull(result);
	}

}
