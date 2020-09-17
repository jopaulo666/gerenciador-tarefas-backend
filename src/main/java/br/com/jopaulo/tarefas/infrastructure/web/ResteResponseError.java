package br.com.jopaulo.tarefas.infrastructure.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ResteResponseError {

	private String error;
	
	private ResteResponseError() {
		
	}

	public String getError() {
		return error;
	}
	
	public static ResteResponseError fromValidationError(Errors errors) {
		ResteResponseError responseError  =new ResteResponseError();
		StringBuilder builder = new StringBuilder();
		
		for (ObjectError error : errors.getAllErrors()) {
			builder.append(error.getDefaultMessage()).append(". ");
		}
		
		responseError.error = builder.toString();
		return responseError;
	}
}
