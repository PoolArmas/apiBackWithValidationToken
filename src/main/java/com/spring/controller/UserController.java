package com.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.dto.ResponseDTO;
import com.spring.model.User;
import com.spring.service.UserService;

/**
 * Class Controller User
 * 
 * @author 56949
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * MEthod getUsers
	 * 
	 * @return
	 */
	@GetMapping("/getUsers")
	public ResponseEntity<Object> getAllUsers() {

		List<User> users = userService.getUsers();
		if (users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}

	}

	/**
	 * MEthod addUser
	 * 
	 * @param userRequest
	 * @param token
	 * @return
	 */
	@PostMapping("/addUser")
	public ResponseEntity<Object> createUser(@RequestBody User userRequest, @RequestAttribute String token) {

		userRequest.setToken(token);
		ResponseDTO responde = userService.addUser(userRequest);

		if (null == responde.getMessage() || responde.getMessage().getMsgDetail().isEmpty()) {
			return new ResponseEntity<>(responde.getUser(), HttpStatus.CREATED);

		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responde.getMessage());
		}

	}

	/**
	 * MEthod updatedUser
	 * 
	 * @param name
	 * @param userRequest
	 * @return
	 */
	@PutMapping("/updatedUser/{name}")
	public ResponseEntity<Object> updateUser(@PathVariable("name") String name, @RequestBody User userRequest) {

		ResponseDTO responde = userService.updatedUser(name, userRequest);

		if (null == responde.getMessage() || responde.getMessage().getMsgDetail().isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(responde.getUser());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responde.getMessage());
		}

	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<Object> deleteAllUsers() {

		ResponseDTO responde = userService.deleteAll();
		if (null == responde.getMessage()) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responde.getMessage());
		}
	}


}
