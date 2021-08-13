package com.db.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.cc.model.Preferences;
import com.db.cc.model.User;
import com.db.cc.repo.PreferencesRepository;
import com.db.cc.repo.UserRepository;
import com.db.cc.request.LoginRequest;
import com.db.cc.request.PreferencesRequest;
import com.db.cc.request.RegistrationRequest;
//import org.apache.commons.lang3.StringUtils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private PreferencesRepository preferencesRepository;
	
	// check if user-name already exists
	public String registerUser(RegistrationRequest registrationRequest)
	{
		User user = new User();
		user.setUsername(registrationRequest.getUsername());
		user.setFirstName(registrationRequest.getFirstName());
		user.setLastName(registrationRequest.getLastName());
		user.setEmail(registrationRequest.getEmail());
		user.setPassword(registrationRequest.getPassword());
		user.setFrequency(registrationRequest.getFrequency());
		
		User test = userRepository.save(user);
		if(test!=null)
			return "User registered successfully!";
		else
			return "Could not register user, please try again!";
	}
	
	// login
	public String login(LoginRequest loginRequest)
	{
		boolean check = userRepository.existsByUsername(loginRequest.getUsername());
		if(check==false)
			return "User does not exist!";
		else
		{
			User user = userRepository.findByUsername(loginRequest.getUsername());
			if(loginRequest.getPassword().equals(user.getPassword()))
				return "Login successfull!";
			else
				return "Incorrect password, please try again!";
		}
	}
	
	// set preferences
	public String setPreferences(PreferencesRequest preferencesRequest)
	{
		Preferences preferences = new Preferences();
		preferences.setUsername(preferencesRequest.getUsername());
		String preferencesString = String.join(",", preferencesRequest.getPreferences());
		preferences.setPreferences(preferencesString);
		
		Preferences test = preferencesRepository.save(preferences);
		if(test!=null)
			return "Preferences set successfully!";
		else
			return "Could not set preferences, please try again!";
		
	}
	
}
