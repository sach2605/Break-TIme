package com.db.cc.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreferencesRequest {
	
	public String username;
	
	public List<String> preferences;

}
