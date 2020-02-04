package com.marcosribeiro.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.marcosribeiro.domain.PaymentWithSlip;

@Service
public class SlipService {

	public void fillPaymentWithSlipe(PaymentWithSlip pgto, Date instant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDueDate(cal.getTime());
	}
}
