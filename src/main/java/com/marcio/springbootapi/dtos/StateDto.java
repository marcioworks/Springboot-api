package com.marcio.springbootapi.dtos;

import java.io.Serializable;

import com.marcio.springbootapi.domain.State;

public class StateDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;

	public StateDto() {
	}

	public StateDto(State obj) {
		id = obj.getId();
		name = obj.getName();
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

}
