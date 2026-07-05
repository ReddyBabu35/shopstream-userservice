package com.shopstream.userservice.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users", schema = "dev01")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="user_id")//better to mention the column name making loosely coupled with java variable names
	private UUID userId;
	
	@Column(name="full_name", nullable = false)
	@Size(max = 150)
	private String fullName; 
	
	@Column(name="email",nullable = false)
	@Email
	private String email;
	
	@Size(max = 20)
	@Column(name="phone")
	private String phone;
	
	@Column(name="password_hash",nullable = false)
	@Size(min = 8, max = 255)
	private String passwordHash;
	
	@Size(max = 50)
	@Column(name="country",nullable = false)
	private String country;
	
	@Size(max = 20)
	@Column(name="language",nullable = false)
	private String language;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name="dob")
	private LocalDate dob;

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

}
