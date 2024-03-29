package com.takeo.controller;
import com.takeo.dto.LoginDto;

import com.takeo.dto.ResetPasswordDto;
import com.takeo.dto.UserDto;

import com.takeo.service.impl.UserServiceImpl;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@Valid @RequestBody UserDto userDto) {
		String userRegistration = userServiceImpl.register(userDto);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, userRegistration);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAll() {
		List<UserDto> users = userServiceImpl.read();

		return ResponseEntity.ok().body(users);
	}

	@PutMapping("/")
	public ResponseEntity<Map<String, String>> putUser(@Valid @RequestBody UserDto userDto) {
		String updateUser = userServiceImpl.update(userDto);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, updateUser);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/verify/{otp}")
	public ResponseEntity<Map<String, String>> verifyOtp(@PathVariable("otp") String otp) {
		String verifyOtp = userServiceImpl.verifyOtp(otp);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, verifyOtp);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginDto loginDto) {
		String login = userServiceImpl.userLogin(loginDto.getEmail(), loginDto.getPassword());
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, login);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/forgotpassword/{email}")
	public ResponseEntity<Map<String, String>> forgotPassword(@PathVariable String email) {
		String resetPassword = userServiceImpl.forgotPassword(email);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, resetPassword);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/changepassword")
	public ResponseEntity<Map<String, String>> changePassword(@Valid @RequestBody ResetPasswordDto resetPassDto) {
		String changePassword = userServiceImpl.changePassword(resetPassDto);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, changePassword);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/addprofilepic")
	public ResponseEntity<Map<String, String>> updateProfilePic(@RequestParam("file") MultipartFile file,
			@RequestParam("email") String email) {
		String updatePicture = userServiceImpl.updateProfilePicture(file, email);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, updatePicture);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/pp/{email}")
	public ResponseEntity<byte[]> getProfilePic(@PathVariable("email") String email) {
		byte[] profilePic = userServiceImpl.viewProfilePicture(email);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(profilePic);
	}


}


