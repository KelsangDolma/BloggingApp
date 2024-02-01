package com.takeo.service;

import com.takeo.dto.CategoryDto;


import java.util.List;

public interface CategoryService {
String create (CategoryDto category);
	
	List<CategoryDto> readAll();
	
	CategoryDto readCategory(Long catid);
	
	String update (CategoryDto category, Long catid);
	
	String delete(Long catid);
}
