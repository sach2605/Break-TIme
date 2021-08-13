package com.db.cc.emailnotification;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send-notification")
public class EmailController {
	
	@Autowired
	private EmailSender emailSender;
	
	@GetMapping()
	public void sendSingleEmail() {
		try {
			emailSender.sendSimpleEmail("apurvadegreat@gmail.com",
					"codingchallengedb23@gmail.com",
					"Notification",
					"This is your reminder to take a break.");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
