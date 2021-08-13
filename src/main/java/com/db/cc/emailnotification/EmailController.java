package com.db.cc.emailnotification;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.db.cc.model.Notifications;
import com.db.cc.model.User;
import com.db.cc.repo.NotificationsRepository;
import com.db.cc.repo.PreferencesRepository;
import com.db.cc.repo.UserRepository;

@RestController
@RequestMapping("/send-notification")
public class EmailController {
	
	@Autowired
	private EmailSender emailSender;
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PreferencesRepository preferencesRepository;
	
	@Autowired
	private NotificationsRepository notificationsRepository;
	
	@GetMapping()
	public void sendSingleEmail() {
		
		List<User> users = userRepository.findAll();
		
		for(User user : users) {
			
			
			
			String preferences[] = preferencesRepository.findByUsername(user.getUsername()).getPreferences().split(",");
			
			List<String> notificationText = new ArrayList<>();
			
			for(String pref : preferences) {
				List<Notifications> notifications = notificationsRepository.findByPreference(pref);
				
				for(Notifications notification: notifications) {
					notificationText.add(notification.getNotificationMessage());
				}
				
			}
			
			Random random = new Random();
			int index = random.nextInt(notificationText.size());
			
			try {
				emailSender.sendSimpleEmail(user.getEmail(),
						"codingchallengedb23@gmail.com",
						"Notification",
						notificationText.get(index));
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
