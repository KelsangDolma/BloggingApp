package com.takeo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;






import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class UserDto {
	private Long uid;

	@NotNull

	@JsonProperty("name")
	private String name;
	
	
	@NotNull
	@JsonProperty("phone")
	private String phone;


	@Email
	@NotBlank
	@JsonProperty("email")
	private String email;
	
	@NotNull
	@JsonProperty("password")
    private String password;

	

	private String image;



	public Long getUid() {
		return uid;
	}



	public void setUid(Long uid) {
		this.uid = uid;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
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



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}
	
	
}
//	