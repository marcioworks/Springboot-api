package com.marcio.springbootapi.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;
	private Long timeStamps;
	
	public StandardError(Integer status, String message, Long timeStamps) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamps = timeStamps;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTimeStamps() {
		return timeStamps;
	}

	public void setTimeStamps(Long timeStamps) {
		this.timeStamps = timeStamps;
	}
	
	
	
	
	
}
