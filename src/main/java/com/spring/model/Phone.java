package com.spring.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "PHONE")
public class Phone {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "number")
	private int number;
	
	@Column(name = "cityCode")
	private int cityCode;
	
	@Column(name = "countrycode")
	private int countrycode;
	
	@JsonBackReference
    @ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public int getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}

	public Phone(long id, int number, int cityCode, int countrycode, User userId) {
		super();
		this.id = id;
		this.number = number;
		this.cityCode = cityCode;
		this.countrycode = countrycode;
		this.user = userId;
	}

	public Phone() {
		super();
	}

}
