package com.takeo.dto;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordDto {
	
	@NotBlank
	@Email(message = "Email address is invalid")
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String confirmPassword;
	

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public ResetPasswordDto(@NotBlank @Email(message = "Email address is invalid") String email,
			@NotBlank String password, @NotBlank String confirmPassword) {
		super();
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

public ResetPasswordDto() {
	// TODO Auto-generated constructor stub
}
}
