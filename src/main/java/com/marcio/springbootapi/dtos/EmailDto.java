package com.marcio.springbootapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String email;
	
	@NotEmpty(message = "Field email cant be empty.")
	@Email(message = "email invalid")
	public EmailDto() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
