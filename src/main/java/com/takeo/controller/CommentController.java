package com.takeo.controller;

import com.takeo.dto.CommentDto;


import com.takeo.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;



@RestController
@RequestMapping("api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/{uid}/{pid}")
	public ResponseEntity<Map<String, String>> createComment(@PathVariable("uid") Long uid,
			@PathVariable("pid") Long pid, @Valid @RequestBody CommentDto commentDto) {
		String createComment = commentService.createComment(uid, pid, commentDto);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, createComment);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{pid}")
	public ResponseEntity<Map<String, List<CommentDto>>> getComments(@PathVariable("pid") Long pid) {
		List<CommentDto> getComments = commentService.getComments(pid);
		String message = "Comments of pid=" + pid;
		Map<String, List<CommentDto>> response = new HashMap<>();
		response.put(message, getComments);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/c/{cid}")
	public ResponseEntity<Map<String, CommentDto>> getComment(@PathVariable("cid") Long cid) {
		CommentDto getComment = commentService.getComment(cid);
		String message = "Comment";
		Map<String, CommentDto> response = new HashMap<>();
		response.put(message, getComment);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PutMapping("/{cid}")
	public ResponseEntity<Map<String, String>> updateComment(@PathVariable("cid") Long cid,
			@RequestBody CommentDto commentDto) {
		String updateComment = commentService.updateComment(cid, commentDto);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, updateComment);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<Map<String, String>> deleteComment(@PathVariable("cid") Long cid) {
		String deleteComment = commentService.deleteComment(cid);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, deleteComment);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}