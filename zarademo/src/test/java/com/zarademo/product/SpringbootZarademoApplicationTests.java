package com.zarademo.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zarademo.product.controller.PricingController;

@SpringBootTest

class SpringbootZarademoApplicationTests {
	@Autowired
	private PricingController restController;
	@Test
	void contextLoads() {
		assertThat(restController).isNotNull();
	}

}
