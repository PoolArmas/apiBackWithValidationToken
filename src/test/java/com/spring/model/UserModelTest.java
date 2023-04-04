package com.spring.model;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserModelTest {
	
	private User user;
	private Phone phone;
	
	@Test
	void userModelTest() {
		
		user = new User();
		user.setEmail("testn@com.cl");
		user.setIsactive(false);
		user.setToken("aaaaa");
		user.setName("test");
		user.setCreated(LocalDateTime.of(2030,01,01,22,22,22));
		user.setLastLogin(LocalDateTime.of(2030,01,01,22,22,22));
		user.setModified(LocalDateTime.of(2030,01,01,22,22,22));
		user.setId(1);
		assertThat(user.getName()).isEqualTo("test");
		assertThat(user.isIsactive()).isFalse();
		assertThat(user.getToken()).isEqualTo("aaaaa");
		assertThat(user.getEmail()).isEqualTo("testn@com.cl");
		assertThat(user.getCreated()).isEqualTo(LocalDateTime.of(2030,01,01,22,22,22));
		assertThat(user.getLastLogin()).isEqualTo(LocalDateTime.of(2030,01,01,22,22,22));
		assertThat(user.getModified()).isEqualTo(LocalDateTime.of(2030,01,01,22,22,22));
		assertThat(user.getId()).isEqualTo(1);
	}
	
	@Test
	void phoneModelTest() {
		User user = new User();
		user.setEmail("testn@com.cl");
		user.setIsactive(false);
		user.setToken("aaaaa");
		user.setName("test");
		
		phone = new Phone();
		phone.setCitycode(1);
		phone.setCountrycode(1);
		phone.setId(1);
		phone.setNumber(1);
		phone.setUser(user);
		
		assertThat(phone.getCitycode()).isEqualTo(1);
		assertThat(phone.getCountrycode()).isEqualTo(1);
		assertThat(phone.getId()).isEqualTo(1);
		assertThat(phone.getNumber()).isEqualTo(1);
		assertThat(phone.getUser()).isEqualTo(user);
	}

}
