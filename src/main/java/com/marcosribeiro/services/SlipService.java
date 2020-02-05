package com.marcosribeiro.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.marcosribeiro.domain.PaymentWithSlip;

@Service
public class SlipService {

	public void fillPaymentWithSlip(PaymentWithSlip pgto, Date orderInstant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderInstant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDueDate(cal.getTime());
	}
}
