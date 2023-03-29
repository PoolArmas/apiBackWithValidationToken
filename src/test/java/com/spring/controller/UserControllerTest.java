package com.spring.controller;

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
import com.spring.model.User;
import com.spring.repository.UserRepository;
import com.spring.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {
	
	@MockBean
	 private UserRepository userRepository;
	
	@MockBean
	 private UserService userService;

	 @Autowired
	 private MockMvc mockMvc;
	 
	 @Autowired
	 private ObjectMapper objectMapper;
	
	@Test
	  void shouldCreateUser() throws Exception {
		
		User user = new User();
		user = new User();
		user.setEmail("testn@com.cl");
		user.setPassword("22Abc");
		user.setIsactive(false);
		user.setToken("aaaaa");
		user.setName("test");

	    mockMvc.perform(post("/api/addUser").contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(user)))
	        .andExpect(status().isCreated())
	        .andDo(print());
	  }
	
	  
	  @Test
	  void shouldReturnListEmptyOfUser() throws Exception {
		  
		List<User> productsEmpty = new ArrayList<>();
	    when(userRepository.findAll()).thenReturn(productsEmpty);
	    mockMvc.perform(get("/api/getUsers"))
	        .andExpect(status().isNoContent())
	        .andDo(print());
	  }
	  
	  
	  @Test
	  void shouldDeleteAllUsers() throws Exception {
	    doNothing().when(userRepository).deleteAll();
	    mockMvc.perform(delete("/api/deleteAll"))
	         .andExpect(status().isNoContent())
	         .andDo(print());
	  }

}
