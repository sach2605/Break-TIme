package com.db.cc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.cc.model.User;

public interface UserRepository extends JpaRepository<User, String>{

	public boolean existsByUsername(String username);
	
	public User findByUsername(String username);
}
