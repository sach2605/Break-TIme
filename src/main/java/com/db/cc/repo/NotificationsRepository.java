package com.db.cc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.cc.model.Notifications;

public interface NotificationsRepository extends JpaRepository<Notifications, Long>{

}
