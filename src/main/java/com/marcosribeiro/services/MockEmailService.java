package com.marcosribeiro.services;


import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger log = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage sm) {
		log.info("Sending simulated email... ");
		log.info(sm.toString());
		log.info("Email has been sent!");
	}

	@Override
	public void sendHtmlEmail(MimeMessage mm) {
		log.info("Sending simulated email... ");
		log.info(mm.toString());
		log.info("Email has been sent!");
	}

}
