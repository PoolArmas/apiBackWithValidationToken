package com.spring.jpa.h2.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jpa.h2.model.Product;
import com.spring.jpa.h2.repository.ProductRepository;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
	
	@MockBean
	 private ProductRepository productRepository;

	 @Autowired
	 private MockMvc mockMvc;
	 
	 @Autowired
	 private ObjectMapper objectMapper;
	
	@Test
	  void shouldCreateProduct() throws Exception {
		
		Product pro = new Product(0, "FAL-1000000", 
				"Descripction", "jeep", "S", new BigDecimal(88.00), "http://test.test", "http://test.test");

	    mockMvc.perform(post("/api/addProduct").contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(pro)))
	        .andExpect(status().isCreated())
	        .andDo(print());
	  }
	
	  @Test
	  void shouldReturnProductByID() throws Exception {
	    String sku = "FAL-1000000";
	    Product pro = new Product(1L, sku, 
				"Descripction", "jeep", "S", new BigDecimal(88.00), "http://test.test", "http://test.test");

	    when(productRepository.findById(1L)).thenReturn(Optional.of(pro));
	    mockMvc.perform(get("/api/productById/{id}", 1L)).andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(1L))
	        .andDo(print());
	  }
	
	  @Test
	  void shouldReturnProductBySku() throws Exception {
	    String sku = "FAL-1000000";
	    Product pro = new Product(0, sku, 
				"Descripction", "jeep", "S", new BigDecimal(88.00), "http://test.test", "http://test.test");

	    when(productRepository.findBySku(sku)).thenReturn((pro));
	    mockMvc.perform(get("/api/productBySku/{sku}", sku)).andExpect(status().isOk())
	        .andExpect(jsonPath("$.sku").value(sku))
	        .andDo(print());
	  }
	  
	  @Test
	  void shouldNotReturnProductBySku() throws Exception {
	    String sku = "FAL-1000000";

	    when(productRepository.findBySku(sku)).thenReturn((null));
	    mockMvc.perform(get("/api/productBySku/{sku}", sku)).andExpect(status()
	    	.isNotFound()).andDo(print());
	  }
	  
	  @Test
	  void shouldReturnListOfProducts() throws Exception {
	    List<Product> products = new ArrayList<>(
	        Arrays.asList(new Product(0, "FAL-1000000", 
					"Descripction", "jeep", "S", new BigDecimal(88.00), "http://test.test", "http://test.test"),
	        		new Product(1, "FAL-1000001", 
	    					"Descripction", "jeep", "S", new BigDecimal(88.00), "http://test.test", "http://test.test"),
	        		new Product(2, "FAL-1000002", 
	    					"Descripction", "jeep", "S", new BigDecimal(88.00), "http://test.test", "http://test.test")));

	    when(productRepository.findAll()).thenReturn(products);
	    mockMvc.perform(get("/api/getProducts"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.size()").value(products.size()))
	        .andDo(print());
	  }
	  
	  @Test
	  void shouldReturnListEmptyOfProducts() throws Exception {
		  
		List<Product> productsEmpty = new ArrayList<>();
	    when(productRepository.findAll()).thenReturn(productsEmpty);
	    mockMvc.perform(get("/api/getProducts"))
	        .andExpect(status().isNoContent())
	        .andDo(print());
	  }
	  
	  @Test
	  void shouldUpdateProduct() throws Exception {
		String sku = "FAL-1000000";

	    Product prod = new Product(1L, sku, 
				"Descripction", "jeep", "S", new BigDecimal(88.00), "http://test.test", "http://test.test");
	    Product prodUpdate = new Product(1L, sku, 
				"Descripction", "jeepUpdated", "S", new BigDecimal(88.00), "http://test.updated", "http://test.test");

	    mockMvc.perform(post("/api/addProduct").contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(prod)));
	    
	    when(productRepository.findById(1L)).thenReturn(Optional.of(prod));

	    mockMvc.perform(put("/api/updatedProduct/{id}", 0).contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(prodUpdate)))
	        .andExpect(status().isNotFound())
	        .andDo(print());
	  }
	  
	  @Test
	  void shouldDeleteProduct() throws Exception {
	    long id = 1L;

	    doNothing().when(productRepository).deleteById(id);
	    mockMvc.perform(delete("/api/deleteProduct/{id}", id))
	         .andExpect(status().isNoContent())
	         .andDo(print());
	  }
	  
	  @Test
	  void shouldDeleteAllProducts() throws Exception {
	    doNothing().when(productRepository).deleteAll();
	    mockMvc.perform(delete("/api/deleteAll"))
	         .andExpect(status().isNoContent())
	         .andDo(print());
	  }

}
