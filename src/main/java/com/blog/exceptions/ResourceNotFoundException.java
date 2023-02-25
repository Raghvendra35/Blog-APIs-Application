package com.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException
{

	String resourceName;
	String FieldName;
	long fieldValue;
	
	

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
	
		super(String.format("%s not found with  %s : %s",resourceName, fieldName,fieldValue));
	
		this.resourceName = resourceName;
		FieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return FieldName;
	}
	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}
	public long getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	
	
}
