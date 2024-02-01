package com.takeo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDto {

    private Long cid;

  @NotBlank
    @JsonProperty("content")
    private String content;

public Long getCid() {
	return cid;
}

public void setCid(Long cid) {
	this.cid = cid;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public CommentDto(Long cid, @NotBlank String content) {
	super();
	this.cid = cid;
	this.content = content;
}
  
 public CommentDto() {
	// TODO Auto-generated constructor stub
}
  
}

   