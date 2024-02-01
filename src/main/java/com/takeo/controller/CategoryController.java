package com.takeo.controller;

import com.takeo.dto.CategoryDto;

import com.takeo.service.impl.CategoryServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl catServiceImpl;

	@PostMapping("/")
	public ResponseEntity<Map<String, String>> createCat(@RequestBody CategoryDto category) {
		String message = "Message";
		String catSave = catServiceImpl.create(category);

		Map<String, String> response = new HashMap<>();

		response.put(message, catSave);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		List<CategoryDto> category = catServiceImpl.readAll();
		String message = "Categories";

		Map<String, List<CategoryDto>> response = new HashMap<>();

		response.put(message, category);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, CategoryDto>> get(@PathVariable("id") Long categoryId) {

		CategoryDto category = catServiceImpl.readCategory(categoryId);
		String message = "Category";
		Map<String, CategoryDto> response = new HashMap<>();
		response.put(message, category);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{cid}")
	public ResponseEntity<Map<String, String>> updateCategory(@PathVariable("cid") long categoryId,
			@RequestBody CategoryDto category) {

		String existingCategory = catServiceImpl.update(category, categoryId);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, existingCategory);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{cid}")
	public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable("cid") long categoryId) {

		String deleteCategory = catServiceImpl.delete(categoryId);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, deleteCategory);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}