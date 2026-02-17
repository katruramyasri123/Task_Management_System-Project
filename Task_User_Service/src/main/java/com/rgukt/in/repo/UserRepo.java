package com.rgukt.in.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rgukt.in.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	public User findByEmail(String email);
}
