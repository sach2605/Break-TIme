package com.db.cc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.db.cc.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	@Transactional
	public void deleteByEmail(String email);

	public boolean existsByEmail(String email);

	public User findByEmail(String email);
}
