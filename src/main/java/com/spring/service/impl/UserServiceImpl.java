package com.spring.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.dto.MessageDTO;
import com.spring.dto.ResponseDTO;
import com.spring.dto.UserDTO;
import com.spring.model.User;
import com.spring.repository.UserRepository;
import com.spring.service.UserService;

/**
 * Class Service User Implements
 * 
 * @author 56949
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * Method created User after validations
	 */
	@Override
	public ResponseDTO addUser(User userRequest) {

		ResponseDTO response = validations(userRequest);
		// WithOut Error
		if (null == response.getMessage()) {
			return saveUser(userRequest, response);
		}
		return response;
	}

	/**
	 * 
	 */
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	/**
	 * 
	 */
	@Override
	public ResponseDTO deleteAll() {
		ResponseDTO response = new ResponseDTO();
		try {
			userRepository.deleteAll();
		} catch (Exception e) {
			response.setMessage(new MessageDTO("Not Found Users!"));
		}
		return response;
	}

	/**
	 * Method INIT Validate - Email
	 * 
	 * @paramuserRequest - user
	 * @return
	 */
	private ResponseDTO validations(User userRequest) {

		ResponseDTO response = validationFormatEmail(userRequest, new ResponseDTO());
		// validation Email Format
		if (null != response.getMessage() && (!response.getMessage().getMsgDetail().isEmpty())) {
			return response;
		}
		return validatePaswwords(userRequest, response);
	}

	/**
	 * Method Validate Password
	 * 
	 * @paramuserRequest
	 * @return
	 */
	private ResponseDTO validatePaswwords(User userRequest, ResponseDTO response) {

		String regex = "^([\\d]{2})([A_Z])([a-z]{2,})(?!\\s)$";

		if (userRequest.getPassword().matches(regex)) {
			return validationExistEmail(userRequest, response);
		} else {
			response.setMessage(new MessageDTO("Error Format Password"));
		}
		return response;
	}

	/**
	 * Method Validate Email Exist In BD
	 * 
	 * @paramuserRequest
	 * @paramResponse
	 * @return
	 */
	private ResponseDTO validationExistEmail(User userRequest, ResponseDTO response) {

		if (null != userRepository.findByEmail(userRequest.getEmail())) {
			response.setMessage(new MessageDTO("Error Email Exist!"));
		}
		return response;
	}

	/**
	 * Method Validate Email
	 * 
	 * @paramuserRequest
	 * @paramResponse
	 * @return
	 */
	private ResponseDTO validationFormatEmail(User userRequest, ResponseDTO response) {

		String regex = "^[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]{2}$";

		if (userRequest.getEmail().matches(regex)) {
			return response;
		} else {
			response.setMessage(new MessageDTO("Error Format Email"));
		}
		return response;
	}

	/**
	 * Created User
	 * 
	 * @param response
	 * @return
	 */
	private ResponseDTO saveUser(User userRequest, ResponseDTO response) {

		userRequest.setCreated(LocalDateTime.now());
		userRequest.setLastLogin(LocalDateTime.now());
		userRequest.setModified(LocalDateTime.now());
		userRequest.setIsactive(true);
		// Save User
		userRepository.save(userRequest);
		// set UserDTO
		response.setUser(createdUserDTOResponse(userRequest));
		return response;
	}

	/**
	 * Method Updated User
	 */
	@Override
	public ResponseDTO updatedUser(String name, User userRequest) {

		ResponseDTO response = validations(userRequest);

		User old = userRepository.findByName(name);

		// Exist User
		if (null != old) {
			// Equals Email, Updated all without Email
			if (userRequest.getEmail().equals(old.getEmail())) {
				User n = old;
				n.setName(userRequest.getName());
				n.setPassword(userRequest.getPassword());
				n.setPhones(userRequest.getPhones());
				n.setLastLogin(LocalDateTime.now());
				n.setModified(LocalDateTime.now());
				userRepository.save(n);
				response.setMessage(null);
				response.setUser(createdUserDTOResponse(n));
				// Email Diferent, but Validations OK
			} else if (null == response.getMessage()) {
				User n = old;
				n.setEmail(userRequest.getEmail());
				n.setName(userRequest.getName());
				n.setPassword(userRequest.getPassword());
				n.setPhones(userRequest.getPhones());
				n.setLastLogin(LocalDateTime.now());
				n.setModified(LocalDateTime.now());
				userRepository.save(n);
				response.setMessage(null);
				response.setUser(createdUserDTOResponse(n));
				// With Error in Validations, return ERROR
			} else {
				return response;
			}
		} else {
			response.setMessage(new MessageDTO("Error Not Found User"));
		}

		return response;

	}

	/**
	 * Created UserDTO by ResponseDTO
	 * 
	 * @param n
	 * @return
	 */
	private UserDTO createdUserDTOResponse(User user) {

		UserDTO userNew = new UserDTO();
		userNew.setisActive(user.isIsactive());
		userNew.setCreated(user.getCreated());
		userNew.setLast_login(user.getLastLogin());
		userNew.setModified(user.getModified());
		userNew.setToken(user.getToken());
		return userNew;
	}

}
