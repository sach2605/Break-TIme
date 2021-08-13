package com.db.cc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.cc.model.Preferences;

public interface PreferencesRepository extends JpaRepository<Preferences, Long>{
	public Preferences findByUsername(String username);
}
