package com.marcio.springbootapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.marcio.springbootapi.domain.Client;

public class ClientDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Field name cant be empty.")
	@Length(min = 5, max = 120, message = " the field name must have between 5 and 120 characters!")
	private String name;

	@NotEmpty(message = "Field email cant be empty.")
	@Email(message = "email invalid")
	private String email;

	public ClientDto() {
	}

	public ClientDto(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
