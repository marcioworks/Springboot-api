package com.marcio.springbootapi.domain.enums;

public enum ClientType {

	PHYSICALPERSON (1, "pessoa fisica"),
	LEGALPERSON(2, "pessoa juridica");

	private Integer cod;
	private String description;
	
	private ClientType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for(ClientType x: ClientType.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		 throw new IllegalArgumentException("Invalid ID: "+ id);
	}
}
