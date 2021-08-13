package com.db.cc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.cc.model.Notifications;

public interface NotificationsRepository extends JpaRepository<Notifications, Long>{
	public List<Notifications> findByPreference(String preference);
}
