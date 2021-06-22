package com.marcio.springbootapi.resources.exceptions;

import java.io.Serializable;

public class FieldMesage implements Serializable{
	private static final long serialVersionUID = 1L;

	private String fieldMessage;
	private String message;
	
	public FieldMesage() {}

	public FieldMesage(String fieldMessage, String message) {
		super();
		this.fieldMessage = fieldMessage;
		this.message = message;
	}

	public String getFieldMessage() {
		return fieldMessage;
	}

	public void setFieldMessage(String fieldMessage) {
		this.fieldMessage = fieldMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
