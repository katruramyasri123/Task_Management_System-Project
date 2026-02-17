package com.rgukt.in.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgukt.in.config.JwtProvider;
import com.rgukt.in.model.User;
import com.rgukt.in.repo.UserRepo;
import com.rgukt.in.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	 private JwtProvider jwtProvider;
	@Autowired
	private UserRepo userRepo;
	@Override
	public User getProfile(String token) {
		String email=jwtProvider.getEmailFromJwtToken(token);
		User user=userRepo.findByEmail(email);
		return user;
	}
	@Override
	public List<User> getUsers(String token) {
		return userRepo.findAll();
	}

}
