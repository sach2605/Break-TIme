package com.db.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.cc.request.LoginRequest;
import com.db.cc.request.PreferencesRequest;
import com.db.cc.request.RegistrationRequest;
import com.db.cc.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/register", produces = "application/json")
	public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest)
	{
		return new ResponseEntity<String>(userService.registerUser(registrationRequest), HttpStatus.OK);
	}
	
	@PostMapping(path = "/login", produces = "application/json")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest)
	{
		return new ResponseEntity<String>(userService.login(loginRequest), HttpStatus.OK);
	}

	@PutMapping(path = "/editPreferences", produces = "application/json")
	public ResponseEntity<String> editPreferences(@RequestBody PreferencesRequest preferencesRequest)
	{
		return new ResponseEntity<String>(userService.editPreferences(preferencesRequest), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/unsubscribe/{username}", produces = "application/json")
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
