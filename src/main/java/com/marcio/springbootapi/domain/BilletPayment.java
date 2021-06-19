package com.marcio.springbootapi.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.marcio.springbootapi.domain.enums.PaymentState;

@Entity
public class BilletPayment extends Payment {
	private static final long serialVersionUID = 1L;
	private Date dueDate;
	private Date paymentDate;

	public BilletPayment() {

	}

	public BilletPayment(Integer id, PaymentState state, Pedido order, Date dueDate, Date paymentDate ) {
		super(id, state, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	

}
