package com.takeo.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RoleDto {
	private Long roleId;
	@NotBlank
	private String role;
	
	
	
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public RoleDto(Long roleId, @NotBlank String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}
	
	public RoleDto() {
		// TODO Auto-generated constructor stub
	}
	

}
