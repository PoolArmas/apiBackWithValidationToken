package com.spring.service;

import java.util.List;
import com.spring.dto.ResponseDTO;
import com.spring.model.User;

/**
 * Class User Service Interface
 * @author 56949
 *
 */
public interface UserService {
	
	public ResponseDTO addUser (User userRequest);
	
	public List<User> getUsers ();
	
	public ResponseDTO updatedUser(String name, User userRequest);
	
	public ResponseDTO deleteAll ();

}
