package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repository.CategoryRepository;
import com.blog.services.CategoryService;

@Service 
public class CategoryServiceImpl implements CategoryService
{
    @Autowired  //property Injection    other one constructor Injection
    private CategoryRepository categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
    
    
    
    
	
    @Override
	public CategoryDto createCategory(CategoryDto categoryDto) 
    {
	 Category cate=this.modelMapper.map(categoryDto, Category.class);
     Category savedCat=this.categoryRepo.save(cate);
	   
     return  this.modelMapper.map(savedCat, CategoryDto.class);
    }

    
    
    
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId)
	{
	  Category cat=this.categoryRepo.findById(categoryId).orElseThrow(
			     ()->new ResourceNotFoundException("Category","Id", categoryId));
	
	   cat.setCategoryTitle(categoryDto.getCategoryTitle());
	   cat.setCategoryDescription(categoryDto.getCategoryDescription());
	   
      Category updateCat=this.categoryRepo.save(cat);
	
    return this.modelMapper.map(updateCat, CategoryDto.class);
   	}

	
	
	
	
	@Override
	public void deleteCategory(Integer categoryId) 
	{
	
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(
			     ()->new ResourceNotFoundException("Category","Id", categoryId));
		
		      this.categoryRepo.deleteById(categoryId);
			
	}

	
	
	
	
	
	@Override
	public CategoryDto getSingleCategory(Integer categoryId)
	{
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(
			     ()->new ResourceNotFoundException("Category","Id", categoryId));	
		
		return this.modelMapper.map(cat, CategoryDto.class);
		
	}

	
	
	
	
	
	@Override
	public List<CategoryDto> getAllCategory()
	{
      List<Category> catList=this.categoryRepo.findAll();	
      //here we can not declare directly this List<Category> and method type list<CategoryDto>
      //So we have to convert All object of category 
                      
                       // here we will get one by one Category and then map using modelMapper and then collect in list
     List<CategoryDto> list=catList.stream().map((cat)-> this.modelMapper.map(catList, CategoryDto.class)).collect(Collectors.toList());
      
      return list;
	}

}















