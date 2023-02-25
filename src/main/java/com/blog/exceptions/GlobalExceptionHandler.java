package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.APIResponse;

@RestControllerAdvice
public class GlobalExceptionHandler 
{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> resourceNotFoundException(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		APIResponse apiResponse=new APIResponse(message, false);
		                                     // message and request is success or flase
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.NOT_FOUND);
	    
		//Controller class mai kabhi Resource NotException aayegi to ye method chal jayega 
		// <APIResponse> ke andar jo bhi response hoga vo user ko mil jayega with status code//response and status code
	} 

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handMethodArgsNotValidException
	                                      (MethodArgumentNotValidException ex)
	{
		Map<String, String> reps=new HashMap();
		ex.getBindingResult().getAllErrors().forEach((error)->
		{
		String fieldName=((FieldError)error).getField();
		String message=error.getDefaultMessage(); //get Message	
		
		reps.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String, String>>(reps, HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<APIResponse> handleAPIException(APIException ex)
	{
		String message=ex.getMessage();
		APIResponse apiResponse=new APIResponse(message, true);
		                                     // message and request is success or flase
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	    		
	} 
	
	
	
}



















