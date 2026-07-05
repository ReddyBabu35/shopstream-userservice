package com.shopstream.userservice.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {

	@NotBlank
	@Size(max = 150)
	private String fullName;

	@NotBlank
	@Email
	private String email;

	@Size(max = 20)
	private String phone;

	@NotBlank
	@Size(min = 8, max = 255)
	private String password;

	@Size(max = 50)
	@NotBlank
	private String country;

	@Size(max = 20)
	@NotBlank
	private String language;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // yyyy-mm-dd
	private LocalDate dob;

	@Override
	public String toString() {
		return "CreateUserRequest [full_name=" + fullName + ", email=" + email + ", phone=" + phone + ", password="
				+ password + ", country=" + country + ", language=" + language + ", dob=" + dob + "]";
	}

	public String getFull_name() {
		return fullName;
	}

	public void setFull_name(String full_name) {
		this.fullName = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String eamil) {
		this.email = eamil;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
