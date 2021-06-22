package com.marcio.springbootapi.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMesage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String message, Long timeStamps) {
		super(status, message, timeStamps);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMesage> getErrors() {
		return errors;
	}

	public void addErrors(String fieldName, String message ) {
		errors.add(new FieldMesage(fieldName, message));
	}

	

}
