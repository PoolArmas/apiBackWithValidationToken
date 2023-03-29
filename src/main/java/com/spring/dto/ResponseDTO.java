package com.spring.dto;


/**
 * Response DTO
 * 
 * @author 56949
 *
 */
public class ResponseDTO {
	
	private MessageDTO message;
	private UserDTO user;
	
	public MessageDTO getMessage() {
		return message;
	}
	public void setMessage(MessageDTO message) {
		this.message = message;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
		

}
