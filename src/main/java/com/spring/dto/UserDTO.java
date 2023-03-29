package com.spring.dto;

import java.time.LocalDateTime;

public class UserDTO {
	
	private long id;
	private LocalDateTime created;
	private LocalDateTime modified;
	private LocalDateTime last_login;
	private String token;
	private boolean isActive;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getModified() {
		return modified;
	}
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	public LocalDateTime getLast_login() {
		return last_login;
	}
	public void setLast_login(LocalDateTime last_login) {
		this.last_login = last_login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setisActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public UserDTO(long id, LocalDateTime created, LocalDateTime modified, LocalDateTime last_login, String token,
			boolean isActive) {
		super();
		this.id = id;
		this.created = created;
		this.modified = modified;
		this.last_login = last_login;
		this.token = token;
		this.isActive = isActive;
	}
	
	public UserDTO() {
		super();
	}
	

}
