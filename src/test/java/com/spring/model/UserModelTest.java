package com.spring.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserModelTest {
	
	private User user;
	
	@Test
	void ProducModelTest() {
		
		user = new User();
		user.setEmail("testn@com.cl");
		user.setIsactive(false);
		user.setToken("aaaaa");
		user.setName("test");
		assertThat(user.getName()).isEqualTo("test");
		assertThat(user.isIsactive()).isFalse();
		assertThat(user.getToken()).isEqualTo("aaaaa");
		assertThat(user.getEmail()).isEqualTo("testn@com.cl");
	}

}
