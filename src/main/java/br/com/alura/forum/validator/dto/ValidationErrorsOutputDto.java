package br.com.alura.forum.validator.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDto {

	
	private List<String> globalErrosMessages = new ArrayList<>();
	private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();
	
	public void addError(String message){
		globalErrosMessages.add(message);
	}
	
	 public void addFieldError(String field, String message){
		 FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field,message);
		 fieldErrors.add(fieldError);
	 }
	 
	 public List<String> globalErrorMessages(){
		 return globalErrosMessages;
	 }
	 
	 public List<FieldErrorOutputDto> getErrors(){
		 return fieldErrors;
	 }
	 
	 public int getNumberofErrors(){
		 return this.globalErrosMessages.size() + this.fieldErrors.size();
	 }
}
