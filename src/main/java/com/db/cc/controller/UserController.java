package com.db.cc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.cc.model.User;
import com.db.cc.repo.UserRepository;
import com.db.cc.request.LoginRequest;
import com.db.cc.request.PreferencesRequest;
import com.db.cc.request.RegistrationRequest;
import com.db.cc.response.LoginResponse;
import com.db.cc.response.ProfileResponse;
import com.db.cc.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path = "/register", produces = "application/json")
	public ResponseEntity<LoginResponse> registerUser(@RequestBody RegistrationRequest registrationRequest)
	{
		return new ResponseEntity<LoginResponse>(userService.registerUser(registrationRequest), HttpStatus.OK);
	}
	
	@PostMapping(path = "/login", produces = "application/json")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
	{
		return new ResponseEntity<LoginResponse>(userService.login(loginRequest), HttpStatus.OK);
	}

//	@PutMapping(path = "/editPreferences", produces = "application/json")
//	public ResponseEntity<String> editPreferences(@RequestBody PreferencesRequest preferencesRequest)
//	{
//		return new ResponseEntity<String>(userService.editPreferences(preferencesRequest), HttpStatus.OK);
//	}
	
	@GetMapping(path = "/getpreferences/{username}")
	public ResponseEntity<?> getPreferences(@PathVariable String username) {
		User user = userRepository.findByEmail(username);
		ProfileResponse profileResponse = new ProfileResponse();
		profileResponse.setFullName(user.getFirstName() + " " + user.getLastName());
		String pref[] = user.getPreferences().split(",");
		profileResponse.setPreferences(Arrays.asList(pref));
		return new ResponseEntity<ProfileResponse>(profileResponse, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getname/{email}")
	public ResponseEntity<?> getName(@PathVariable String email) {
		User user = userRepository.findByEmail(email);
		String name = user.getFirstName() + " " + user.getLastName();
		return new ResponseEntity<String>(name, HttpStatus.OK);
	}
	
	@GetMapping(path = "/unsubscribe/{username}", produces = "application/json")
	public ResponseEntity<String> unsubscribe(@PathVariable("username") String username)
	{
		return new ResponseEntity<String>(userService.unsubscribe(username),HttpStatus.OK);
	}
	
	@PutMapping(path = "/logout/{username}", produces = "application/json")
	public ResponseEntity<String> logout(@PathVariable("username") String username)
	{
		return new ResponseEntity<String>(userService.logout(username), HttpStatus.OK);
	}
	
//	@PostMapping(path = "/setPreferences", produces = "application/json")
//	public ResponseEntity<String> setPreferences(@RequestBody PreferencesRequest preferencesRequest)
//	{
//		return new ResponseEntity<String>(userService.setPreferences(preferencesRequest), HttpStatus.OK);
//	}

}
