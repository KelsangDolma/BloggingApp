package com.takeo.dto;
import java.util.List;

import com.takeo.entities.Category;
import com.takeo.entities.Comment;
import com.takeo.entities.Post;
import com.takeo.entities.Role;
import com.takeo.entities.User;

import lombok.Data;

@Data
public class ReportDto {
	private List<Role> roles;
	private List<User> users;
	private List<Category> categories;
	private List<Post> posts;
	private List<Comment> comments;
	
	
	
}
