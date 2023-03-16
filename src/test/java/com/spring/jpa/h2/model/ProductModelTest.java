package com.spring.jpa.h2.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductModelTest {
	
	private Product product;
	
	@Test
	void ProducModelTest() {
		
		product = new Product();
		product.setBrand("jeep");
		product.setId(1);
		product.setName("Descripction");
		product.setOtherImagen("http://test.test");
		product.setPrice(new BigDecimal(88.00));
		product.setPrincipalImagen("http://test.test");
		product.setSize("S");
		product.setSku("FAL-1000000");
		product.toString();
		assertThat(product.getBrand()).isEqualTo("jeep");
		assertThat(product.getId()).isEqualTo(1);
		assertThat(product.getOtherImagen()).isEqualTo("http://test.test");
		assertThat(product.getPrice()).isEqualTo(new BigDecimal(88.00));
		assertThat(product.getPrincipalImagen()).isEqualTo("http://test.test");
		assertThat(product.getSize()).isEqualTo("S");
		assertThat(product.getSku()).isEqualTo("FAL-1000000");
		assertThat(product.getName()).isEqualTo("Descripction");
	}

}
