package com.spring.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.dto.MessageDTO;
import com.spring.dto.ResponseDTO;
import com.spring.dto.UserDTO;
import com.spring.model.Phone;
import com.spring.model.User;
import com.spring.repository.PhoneRepository;
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

	@Autowired
	PhoneRepository phoneRepository;

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
	 * @param userRequest - user
	 * @return  ResponseDTO
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
	 * Method Validate Email
	 *
	 * @param userRequest - request
	 * @param response - res
	 * @return ResponseDTO obj
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
	 * Method Validate Password
	 * 
	 * @param userRequest request
	 * @return ResponseDTO
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
	 * @param userRequest  - obj
	 * @param response res
	 * @return ResponseDTO res
	 */
	private ResponseDTO validationExistEmail(User userRequest, ResponseDTO response) {

		if (null != userRepository.findByEmail(userRequest.getEmail())) {
			response.setMessage(new MessageDTO("Error Email Exist!"));
		}
		return response;
	}

	/**
	 * Save User
	 * @param userRequest - request
	 * @param response - res
	 * @return ResponseDTO obj
	 */
	private ResponseDTO saveUser(User userRequest, ResponseDTO response) {

		userRequest.setCreated(LocalDateTime.now());
		userRequest.setLastLogin(LocalDateTime.now());
		userRequest.setModified(LocalDateTime.now());
		userRequest.setIsactive(true);
		// Save User
		userRepository.save(userRequest);
		// save Phone
		savePhone(userRepository.findByEmail(userRequest.getEmail()), userRequest.getPhones());
		// set UserDTO
		response.setUser(createdUserDTOResponse(userRequest));
		return response;
	}

	/**
	 * Save Phones
	 *
	 * @param findByEmail - User
	 * @param phones . List
	 */
	private void savePhone(User findByEmail, List<Phone> phones) {
		phones.forEach(phone -> {
			Phone pho = phoneRepository.findByCityCode(phone.getCityCode());
			pho.setUser(findByEmail);
			phoneRepository.save(pho);
		});
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
				n.setLastLogin(LocalDateTime.now());
				n.setModified(LocalDateTime.now());
				userRepository.save(n);
				updatedPhones(userRequest, n);
				response.setMessage(null);
				response.setUser(createdUserDTOResponse(n));
				// Email Different, but Validations OK
			} else if (null == response.getMessage()) {
				User n = old;
				n.setEmail(userRequest.getEmail());
				n.setName(userRequest.getName());
				n.setPassword(userRequest.getPassword());
				n.setLastLogin(LocalDateTime.now());
				n.setModified(LocalDateTime.now());
				userRepository.save(n);
				updatedPhones(userRequest, n);
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
	 * Method UpdatedPhones
	 *
	 * @param userRequest - request
	 * @param n Data User
	 */
	private void updatedPhones(User userRequest, User n) {

		// delete Phones by userId
		phoneRepository.findAll().forEach( phone ->{
			if (phone.getUser().getId() == n.getId()) {
				phoneRepository.delete(phone);
			}
		});
		// Created new Phones
		userRequest.getPhones().forEach(phone ->{
			phone.setUser(n);
			phoneRepository.save(phone);
		});
	}

	/**
	 * Created UserDTO by ResponseDTO
	 * 
	 * @param user - data
	 * @return UserDTO Response
	 */
	private UserDTO createdUserDTOResponse(User user) {

		UserDTO userNew = new UserDTO();
		userNew.setisActive(user.isIsactive());
		userNew.setCreated(user.getCreated());
		userNew.setLast_login(user.getLastLogin());
		userNew.setModified(user.getModified());
		userNew.setToken(user.getToken());
		userNew.setUser(user);
		return userNew;
	}

}
