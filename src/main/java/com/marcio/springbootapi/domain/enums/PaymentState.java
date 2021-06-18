package com.marcio.springbootapi.domain.enums;

public enum PaymentState {

	PENDING(1, "pendente"),
	PAYED(2, "pago"),
	CANCELED(3, "cancelado");
	
	private Integer cod;
	private String description;
	
	private PaymentState(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static PaymentState toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(PaymentState x : PaymentState.values()) {
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid cod: "+ cod);
	}
	
	
}
