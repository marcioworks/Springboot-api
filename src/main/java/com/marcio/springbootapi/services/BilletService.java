package com.marcio.springbootapi.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.BilletPayment;

@Service
public class BilletService {

	public void FillBilletPayment(BilletPayment payment, Date orderDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orderDate);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		payment.setDueDate(calendar.getTime());
	}
}
