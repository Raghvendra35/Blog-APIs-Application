  // this interface is using for loose cupling so that we can change implementation

package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService
{

	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//updata
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	public void deleteCategory(Integer categoryId);
	
	//getSingle
	
	public CategoryDto getSingleCategory(Integer categoryId);
	
	//get
	public List<CategoryDto> getAllCategory();
	
}
