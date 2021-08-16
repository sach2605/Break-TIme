package com.db.cc.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.db.cc.model.Preferences;
import com.db.cc.model.User;
import com.db.cc.repo.PreferencesRepository;
import com.db.cc.repo.UserRepository;
import com.db.cc.request.LoginRequest;
import com.db.cc.request.PreferencesRequest;
import com.db.cc.request.RegistrationRequest;
import com.db.cc.response.LoginResponse;
//import org.apache.commons.lang3.StringUtils;

@Service
@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private PreferencesRepository preferencesRepository;
	
	// check if user-name already exists
	public LoginResponse registerUser(RegistrationRequest registrationRequest)
	{
		User user = new User();
		user.setFirstName(registrationRequest.getFirstName());
		user.setLastName(registrationRequest.getLastName());
		user.setEmail(registrationRequest.getEmail());
		user.setPassword(registrationRequest.getPassword());
		user.setFrequency(registrationRequest.getFrequency());
		String preferencesString = String.join(",", registrationRequest.getPreferences());
		user.setNotificationsStatus("active");
		user.setPreferences(preferencesString);
		
		LoginResponse loginResponse = new LoginResponse();
		
		User test = userRepository.save(user);
		if(test!=null) {
			loginResponse.setMessage("User registered successfully!");
			loginResponse.setUsername(test.getFirstName() + " " + test.getLastName());
			loginResponse.setPreferences(registrationRequest.getPreferences());
		}
		else {
			loginResponse.setMessage("Could not register user, please try again!");
		}
		
		return loginResponse;
	}
	
	// login
	public LoginResponse login(LoginRequest loginRequest)
	{
		boolean check = userRepository.existsByEmail(loginRequest.getEmail());
		LoginResponse loginResponse = new LoginResponse();
		
		if(check==false) {
			loginResponse.setMessage("User does not exist!");
		}
		else
		{
			User user = userRepository.findByEmail(loginRequest.getEmail());
			if(loginRequest.getPassword().equals(user.getPassword())) {
				loginResponse.setMessage("Login successfull");
				loginResponse.setUsername(user.getFirstName() + " " + user.getLastName());
				String[] preferences = user.getPreferences().split(",");
				List<String> list = Arrays.asList(preferences);
				loginResponse.setPreferences(list);
			}
			else {
				loginResponse.setMessage("Incorrect password, please try again!");
			}
			
		}
		
		return loginResponse;
	}
	
//	public String editPreferences(PreferencesRequest preferencesRequest)
//	{
//		String preferencesString = String.join(",", preferencesRequest.getPreferences());
//		User user = userRepository.findByUsername(preferencesRequest.getUsername());
//		user.setPreferences(preferencesString);
//		User test = userRepository.save(user);
//		
//		if(test!=null)
//			return "Preferences updated successfully!";
//		else
//			return "Could not update preferences, please try again!";
//		
//	}
	
	public String unsubscribe(String username)
	{
		userRepository.deleteByEmail(username);
		return "You have successfully unsubscribed!";
		
	}
	
	public String logout(String username)
	{
		User user = userRepository.findByEmail(username);
		user.setNotificationsStatus("inactive");
		userRepository.save(user);
		
		return "Logged out successfully!";
	}
	
	// set preferences
//	public String setPreferences(PreferencesRequest preferencesRequest)
//	{	
//		Preferences preferences = new Preferences();
//		preferences.setUsername(preferencesRequest.getUsername());
//		String preferencesString = String.join(",", preferencesRequest.getPreferences());
//		preferences.setPreferences(preferencesString);
//		
//		Preferences test = preferencesRepository.save(preferences);
//		if(test!=null)
//			return "Preferences set successfully!";
//		else
//			return "Could not set preferences, please try again!";
//		
//	}
	
}
