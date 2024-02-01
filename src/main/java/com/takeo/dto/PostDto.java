package com.takeo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class PostDto {

    private Long pid;

    @NotBlank
    @JsonProperty("title")

	private String title;

    
    @NotBlank
    @JsonProperty("content")
    private String content;
    
    private String image;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public PostDto(Long pid, @NotBlank String title, @NotBlank String content, String image) {
		super();
		this.pid = pid;
		this.title = title;
		this.content = content;
		this.image = image;
	}
    
    public PostDto() {
		// TODO Auto-generated constructor stub
	}
    
}

    

