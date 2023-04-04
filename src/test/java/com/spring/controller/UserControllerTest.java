package com.spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

	 
	 @Test
	 void testDeleteAllUser() throws Exception {

	     RequestBuilder request = MockMvcRequestBuilders
	             .delete("/api/deleteAll")
	             .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmZWNoYVRva2VuIjoiMjAyMy0wNC0wMyJ9.Q3XLRfuJb9uQ5G0MZVYOaFdJq09BDRxurK5oVMY0gjI")
	             .accept(MediaType.APPLICATION_JSON);
	     mockMvc.perform(request).andReturn();
	 }
	 
	 
	 @Test
	 void testGetUsers() throws Exception {

	     RequestBuilder request = MockMvcRequestBuilders
	             .get("/api/getUsers")
	             .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmZWNoYVRva2VuIjoiMjAyMy0wNC0wMyJ9.Q3XLRfuJb9uQ5G0MZVYOaFdJq09BDRxurK5oVMY0gjI")
	             .accept(MediaType.APPLICATION_JSON);
	     mockMvc.perform(request).andReturn();
	 }
	 
	 @Test
	 void testUpdatedUser() throws Exception {

	     RequestBuilder request = MockMvcRequestBuilders
	             .get("/api/updatedUser/{name}","test")
	             .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmZWNoYVRva2VuIjoiMjAyMy0wNC0wMyJ9.Q3XLRfuJb9uQ5G0MZVYOaFdJq09BDRxurK5oVMY0gjI")
	             .accept(MediaType.APPLICATION_JSON);
	     mockMvc.perform(request).andReturn();
	 }
	
	@Test
	  void shouldCreateUser() throws Exception {
		
		User user = new User();
		user = new User();
		user.setEmail("testn@com.cl");
		user.setPassword("22Abc");
		user.setIsactive(false);
		user.setToken("aaaaa");
		user.setName("test");

		 RequestBuilder request = MockMvcRequestBuilders
	             .post("/api/addUser")
	             .content(new ObjectMapper().writeValueAsString(user))
	             .requestAttr("token", "kjhgdjhgdjhdf")
	             .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmZWNoYVRva2VuIjoiMjAyMy0wNC0wMyJ9.Q3XLRfuJb9uQ5G0MZVYOaFdJq09BDRxurK5oVMY0gjI")
	             .accept(MediaType.APPLICATION_JSON);
	     mockMvc.perform(request).andReturn();
	  }

}
