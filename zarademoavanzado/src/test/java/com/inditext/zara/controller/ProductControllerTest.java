package com.inditext.zara.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.inditext.zara.model.Product;
import com.inditext.zara.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	public void testGetAllProducts() throws Exception {
		Product product1 = new Product(1L, "Product 1");
		Product product2 = new Product(2L, "Product 2");
		List<Product> productList = Arrays.asList(product1, product2);

		Mockito.when(productService.getAllProducts()).thenReturn(productList);

		mockMvc.perform(MockMvcRequestBuilders.get("/products")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Product 1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Product 2")).andDo(print());
	}

	@Test
	public void testGetProductById() throws Exception {
		Product product = new Product(1L, "Product 1");

		Mockito.when(productService.getProductById(1L)).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", 1L)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product 1")).andDo(print());
	}

	@Test
	public void testCreateProduct() throws Exception {
		Product product = new Product(1L, "Product 1");

		Mockito.when(productService.createProduct(Mockito.any(Product.class))).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.post("/products").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Product 1\"}")).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product 1")).andDo(print());
	}

	@Test
	public void testUpdateProduct() throws Exception {
		Product updatedProduct = new Product(1L, "Updated Product");

		Mockito.when(productService.updateProduct(anyLong(), Mockito.any(Product.class))).thenReturn(updatedProduct);

		mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Updated Product\"}")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Product")).andDo(print());
	}

	@Test
	public void testDeleteProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", 1L)).andExpect(status().isNoContent())
				.andDo(print());

		Mockito.verify(productService, Mockito.times(1)).deleteProduct(1L);
	}
}