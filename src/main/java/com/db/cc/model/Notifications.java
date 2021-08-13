package com.db.cc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notifications {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long notificationId;
	
	private String preference;
	
	private String notificationMessage;
	
}