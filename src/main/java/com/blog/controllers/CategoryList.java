package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.APIResponse;
import com.blog.payloads.CategoryDto;
import com.blog.services.impl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryList
{

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	
	 // create
	 @PostMapping()
     public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
     {
       CategoryDto  createCategory=this.categoryServiceImpl.createCategory(categoryDto);
       return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    	 
     }
	
	
	 //update
     @PutMapping("/{id}")
     public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable int id)
     {
       CategoryDto  updateCategory=this.categoryServiceImpl.updateCategory(categoryDto, id);
       return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    	 
     }
	 
	 //detele
	 @DeleteMapping("/{id}")
     public ResponseEntity<APIResponse> deleteCategory(@PathVariable int id)
     {
       this.categoryServiceImpl.deleteCategory(id);
       
       return new ResponseEntity<APIResponse>
       (new APIResponse("Category has been deleted !!!", true), HttpStatus.OK);
       
    	 
     }
	 
	 
	 //getSingle
	 @GetMapping("/{id}")
     public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable int id)
     {
      CategoryDto cat=this.categoryServiceImpl.getSingleCategory(id);
       
       return new ResponseEntity<CategoryDto>(cat, HttpStatus.OK);
       
     }
	
	
	 //GetAll
	 @GetMapping()
     public ResponseEntity<List<CategoryDto>> getAllCategory()
     {
      List<CategoryDto> cat=this.categoryServiceImpl.getAllCategory();
       
       return ResponseEntity.ok(cat);
    
     }

}






















