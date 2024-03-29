package com.takeo.controller;

import com.takeo.dto.PostDto;

import com.takeo.service.impl.PostServiceImpl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/post")
public class PostController {
	@Autowired
	private PostServiceImpl postServiceImpl;

	@PostMapping("/{uid}/{catId}")
	public ResponseEntity<Map<String, String>> createPost(@PathVariable("uid") Long uid,@PathVariable("catId") String catName,
			@Valid @RequestBody PostDto postDto) {

		String message = "Message";
		String postSave = postServiceImpl.create(postDto, uid,catName);

		Map<String, String> response = new HashMap<>();

		response.put(message, postSave);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/users/{uid}")
	public ResponseEntity<?> getAllFromUser(@PathVariable("uid") long uid) {
		List<PostDto> posts = postServiceImpl.read(uid);
		String message = "Posts:";
		Map<String, List<PostDto>> response = new HashMap<>();
		response.put(message, posts);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		List<PostDto> posts = postServiceImpl.read();
		String message = "Posts:";
		Map<String, List<PostDto>> response = new HashMap<>();
		response.put(message, posts);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/bycategory")
	public ResponseEntity<?> getByCat(@RequestParam(name="cat") String cat,Pageable pageable) {
		Page<PostDto> category = postServiceImpl.readCatPost(cat,pageable);

		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, PostDto>> get(@PathVariable("id") Long pid) {
		PostDto post = postServiceImpl.readPost(pid);
		String message = "Posts:";
		Map<String, PostDto> response = new HashMap<>();
		response.put(message, post);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
  
	@PutMapping("/{uid}/{pid}")
	public ResponseEntity<Map<String, String>> updatepost(@PathVariable("pid") long pid, @PathVariable("uid") long uid,
			@Valid @RequestBody PostDto post) {
		String existingPost = postServiceImpl.update(post, uid, pid);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, existingPost);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deletePost(@PathVariable("id") long pid) {
		String deletePost = postServiceImpl.delete(pid);
		String message = "Message";
		
		Map<String, String> response = new HashMap<>();

		response.put(message, deletePost);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/postpic")
	public ResponseEntity<Map<String, String>> updatePostPic(@RequestParam("file") MultipartFile file,
			@RequestParam("pid") Long pid) {
		String updatePicture = postServiceImpl.updatePostPicture(file, pid);
		String message = "Message";

		Map<String, String> response = new HashMap<>();

		response.put(message, updatePicture);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/postpic/{pid}")
	public ResponseEntity<byte[]> updatePostPic(@PathVariable("pid") Long pid) {
		byte[] postPic = postServiceImpl.viewPostPicture(pid);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(postPic);
	}
	

}

