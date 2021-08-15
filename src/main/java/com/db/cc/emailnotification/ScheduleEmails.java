package com.db.cc.emailnotification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.db.cc.model.Notifications;
import com.db.cc.model.User;
import com.db.cc.repo.NotificationsRepository;
import com.db.cc.repo.PreferencesRepository;
import com.db.cc.repo.UserRepository;

@Component
public class ScheduleEmails {
	
	@Autowired
	private EmailSender emailSender;
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PreferencesRepository preferencesRepository;
	
	@Autowired
	private NotificationsRepository notificationsRepository;
	
	private final long twohours = 3600 * 1000 * 2;
	
	
	@Scheduled(fixedRate = twohours)
	public void scheduleTaskWithFixedRate() throws ParseException {
		
		System.out.println("running scheduled task");
		
		LocalTime currentTime = LocalTime.now();
		LocalTime nine = LocalTime.parse("09:00");
		LocalTime eighteen = LocalTime.parse("18:00");
		
		try {
			
			if(currentTime.isAfter(nine) && currentTime.isBefore(eighteen)) {
				System.out.println("can send email");
				List<User> users = userRepository.findAll();
				List<Notifications> notifications = notificationsRepository.findAll();
				
				
				for(User user : users) {
					
					if(!user.getNotificationsStatus().equalsIgnoreCase("active")) {
						continue;
					}
					
					String preferences[] = user.getPreferences().split(",");
					
					List<String> notificationText = new ArrayList<>();
					
					for(String pref : preferences) {
						
						
						for(Notifications notification: notifications) {
							if(notification.getPreference().equalsIgnoreCase(pref) ) {
								notificationText.add(notification.getNotificationMessage());
							}
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
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
			
		
		
	}
	
}
