package com.takeo.dto;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LoginDto {
	
	@Email(message = "Email address is invalid")
	@NotBlank
	private String email;

	@NotBlank
	private String password;

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

	public LoginDto(@Email(message = "Email address is invalid") @NotBlank String email, @NotBlank String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
public LoginDto() {
	// TODO Auto-generated constructor stub
}

}
