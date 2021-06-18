package com.marcio.springbootapi.domain;

import javax.persistence.Entity;

import com.marcio.springbootapi.domain.enums.PaymentState;

@Entity
public class CardPayment extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;
	
	public CardPayment() {
	}
	
	public CardPayment(Integer id, PaymentState state, Order order,Integer numberOfInstallments) {
		super(id, state, order);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	
	
	
}
