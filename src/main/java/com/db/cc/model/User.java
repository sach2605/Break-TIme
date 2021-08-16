package com.db.cc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

	
	private String firstName;
	
	private String lastName;
	
	@Id
	private String email;
	
	private String password;
	
	private int frequency; // 2, 4 or 6
	
	private String preferences;
	
	private String notificationsStatus;
	
}
