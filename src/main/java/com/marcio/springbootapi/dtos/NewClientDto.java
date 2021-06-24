package com.marcio.springbootapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.marcio.springbootapi.services.validations.ClientInsert;

@ClientInsert
public class NewClientDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Field name cant be empty.")
	@Length(min = 5, max = 120, message = " the field name must have between 5 and 120 characters!")
	private String name;
	
	@NotEmpty(message = "Field email cant be empty.")
	@Email
	private String email;
	@NotEmpty(message = "Field cant be empty.")
	private String cpfOrCnpj;
	private Integer type;

	@NotEmpty(message = "Field name cant be empty.")
	private String street;
	@NotEmpty(message = "Field name cant be empty.")
	private String number;
	private String complement;
	private String neighborood;
	@NotEmpty(message = "Field name cant be empty.")
	private String zipcode;

	@NotEmpty(message = "Field name cant be empty.")
	private String phone1;
	private String phone2;
	private String phone3;

	private Integer cityId;

	public NewClientDto() {
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

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborood() {
		return neighborood;
	}

	public void setNeighborood(String neighborood) {
		this.neighborood = neighborood;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

}
