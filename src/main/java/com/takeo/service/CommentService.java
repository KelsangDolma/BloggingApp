package com.takeo.service;

import com.takeo.dto.CommentDto;


import java.util.List;

public interface CommentService {
	
	
	public String createComment(Long uid, Long pid, CommentDto commentDto);

	public List<CommentDto> getComments(Long pid);

	public CommentDto getComment(Long cid);
	
	public String updateComment(Long cid,CommentDto commentDto);

	public String deleteComment(Long cid);

}
