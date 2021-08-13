package com.db.cc.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

	public String username;
	
	public String firstName;
	
	public String lastName;
	
	public String email;
	
	public String password;
	
	public int frequency; 
	
}
