package com.db.cc.emailnotification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfiguration {
	
	@Bean
	public EmailSender emailSender() {
		return new EmailSender();
	}
	
}
