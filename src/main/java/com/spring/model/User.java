package com.spring.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER_TEST")
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	
	@Column(name = "Name")
	private String name;

	@Email (message = "Error de formato de correo electrónico")
	@NotEmpty (message = "Error, Email Empty!")
	@Column(name = "Email")
	private String email;
	
	@NotEmpty (message = "Error, Password Empty!")
	@Column(name = "Password")
	private String password;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Phone> phones;
	
	@JsonIgnore
	@Column(name = "Token")
	private String token;
	
	@JsonIgnore
	@Column(name = "Created")
	private LocalDateTime created;
	
	@JsonIgnore
	@Column(name = "last_login")
	private LocalDateTime lastLogin;
	
	@JsonIgnore
	@Column(name = "modified")
	private LocalDateTime modified;
	
	@Column(name = "isActive")
	private boolean isactive;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public User(long id, String name,
			@Email(message = "Error de formato de correo electrónico") @NotEmpty(message = "Error, Email Empty!") String email,
			@NotEmpty(message = "Error, Password Empty!") String password, List<Phone> phones, String token,
			LocalDateTime created, LocalDateTime lastLogin, LocalDateTime modified, boolean isactive) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = phones;
		this.token = token;
		this.created = created;
		this.lastLogin = lastLogin;
		this.modified = modified;
		this.isactive = isactive;
	}

	public User() {
		super();
	}
		
}
